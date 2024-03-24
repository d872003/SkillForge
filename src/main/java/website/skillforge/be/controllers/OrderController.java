package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.createDTO.OrderedDTO;
import website.skillforge.be.entities.*;
import website.skillforge.be.entities.ordered.OrderedDetail;
import website.skillforge.be.entities.ordered.Ordered;
import website.skillforge.be.enums.Role;
import website.skillforge.be.enums.status.OrderStatus;
import website.skillforge.be.repository.*;
import website.skillforge.be.util.AccountUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class OrderController {
    @Autowired
    private AccountUtil accountUtils;
    @Autowired
    private OrderedDetailRepository orderedDetailRepository;
    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    EnrollController enroll;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/order")
    public ResponseEntity<?> createUrl(@RequestBody OrderedDTO orderedDTO) throws NoSuchAlgorithmException, InvalidKeyException, Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);

        List<Long> courseIds = orderedDTO.getCourseId();
        Ordered ordered = new Ordered();
        ordered.setCreatedDate(new Date());
        ordered.setStatus(OrderStatus.ODERED);
        ordered.setTotalPrice(orderedDTO.getTotalPrice());
        ordered.setAccount(accountUtils.getCurrentAccount());
        orderedRepository.save(ordered);

        String tmnCode = "C7SGRW8H";
        String secretKey = "MJMALIFHTSTRGJYLHSVVRBSYLJPMXZPK";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://localhost:5173/success";
        String currCode = "VND";
        String id = String.valueOf(ordered.getId());
        String price = String.valueOf(orderedDTO.getTotalPrice() * 100);
        List<OrderedDetail> orderedDetails = new ArrayList<>();

        for (Long courseId : courseIds) {
            OrderedDetail orderedDetail = new OrderedDetail();
            orderedDetail.setCreatedDate(new Date());
            orderedDetail.setStatus(OrderStatus.ODERED);
            orderedDetail.setAccount(accountUtils.getCurrentAccount());
            orderedDetail.setCourseId(courseId);
            orderedDetail.setPrice(courseRepository.findCourseById(courseId).getPrice());
            orderedDetail.setOrdered(ordered);
            OrderedDetail newOrder = orderedDetailRepository.save(orderedDetail);
            orderedDetails.add(newOrder);
        }
        ordered.setOrderedDetails(orderedDetails);

        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", id);
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + id);
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", price);
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "http://skillforge.website/");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'
        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);
        vnpParams.put("vnp_SecureHash", signed);
        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'
        return ResponseEntity.ok(urlBuilder.toString());
    }

    @GetMapping("/update-order")
    public Ordered orderSuccess(@RequestParam long orderId) {
        Ordered ordered = orderedRepository.findOrderedById(orderId);
        ordered.setStatus(OrderStatus.PAID);
        List<OrderedDetail> orderedDetails = orderedDetailRepository.findOrderedDetailByOrderedId(orderId);
        for (OrderedDetail orderedDetail : orderedDetails) {
            Course course = courseRepository.findCourseById(orderedDetail.getCourseId());
            orderedDetail.setStatus(OrderStatus.PAID);
            enroll.enrollCourseAfterPay(orderedDetail.getCourseId());


            Account admin = accountRepository.findAccountByRole(Role.ADMIN);
            Account student = ordered.getAccount();
            Account teacher = course.getCreateBy();

            Wallet adwallet = admin.getWallet();
            Wallet studentWallet = student.getWallet();
            Wallet teacherWallet = teacher.getWallet();

            if (adwallet == null) {
                Wallet wallet = new Wallet();
                wallet.setBalance(0);
                wallet.setAccount(admin);
                admin.setWallet(wallet);
                adwallet = walletRepository.save(wallet);
            }

            if (studentWallet == null) {
                Wallet wallet = new Wallet();
                wallet.setBalance(0);
                wallet.setAccount(student);
                student.setWallet(wallet);
                studentWallet = walletRepository.save(wallet);
            }

            if (teacherWallet == null) {
                Wallet wallet = new Wallet();
                wallet.setBalance(0);
                wallet.setAccount(teacher);
                teacher.setWallet(wallet);
                teacherWallet = walletRepository.save(wallet);
            }

            Transactions transactions = new Transactions();
            transactions.setFrom(null);
            transactions.setTo(studentWallet);
            transactions.setMoney(course.getPrice());
            transactions.setOrderedDetail(orderedDetail);
            orderedDetail.getTransactions().add(transactions);
            transactionRepository.save(transactions);

            Transactions transactions2 = new Transactions();
            transactions2.setFrom(studentWallet);
            transactions2.setTo(adwallet);
            transactions2.setMoney(course.getPrice());
            transactions2.setOrderedDetail(orderedDetail);
            orderedDetail.getTransactions().add(transactions2);
            transactionRepository.save(transactions2);

            Transactions transactions3 = new Transactions();
            transactions3.setFrom(adwallet);
            transactions3.setTo(teacherWallet);
            transactions3.setMoney(course.getPrice() * 0.9);
            transactions3.setOrderedDetail(orderedDetail);
            orderedDetail.getTransactions().add(transactions3);
            transactionRepository.save(transactions3);

            adwallet.setBalance(adwallet.getBalance() + course.getPrice() * 0.1);
            teacherWallet.setBalance(teacherWallet.getBalance() + course.getPrice() * 0.9);
            walletRepository.save(adwallet);
            walletRepository.save(teacherWallet);
        }


        return orderedRepository.save(ordered);
    }

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}
