//package com.wangquocthai.java4.lab1;
//
//public class UserTest {
//    public static void main(String[] args) {
//        UserManager userManager = new UserManager();
//        String testUserId = "testuser99";
//
//        // ----- 1. TEST CREATE -----
//        System.out.println("--- 1. ĐANG TEST CREATE ---");
//        User newUser = new User(testUserId, "pass99", "Tester Mới", "test99@gmail.com", false);
//        userManager.create(newUser);
//
//        // ----- 2. TEST FINDALL -----
//        System.out.println("\n--- 2. ĐANG TEST FINDALL (sau khi tạo) ---");
//        userManager.findAll();
//
//        // ----- 3. TEST UPDATE -----
//        System.out.println("\n--- 3. ĐANG TEST UPDATE ---");
//        User userToUpdate = userManager.findById(testUserId);
//        if (userToUpdate != null) {
//            userToUpdate.setFullname("Tester Đã Đổi Tên");
//            userToUpdate.setAdmin(true);
//            userManager.update(userToUpdate);
//        }
//
//        // ----- 4. TEST FINDBYID -----
//        System.out.println("\n--- 4. ĐANG TEST FINDBYID (sau khi cập nhật) ---");
//        userManager.findById(testUserId);
//
//        // ----- 5. TEST DELETE -----
//        System.out.println("\n--- 5. ĐANG TEST DELETE ---");
//        userManager.deleteById(testUserId);
//
//        // ----- 6. TEST FINDALL (sau khi xóa) -----
//        System.out.println("\n--- 6. ĐANG TEST FINDALL (sau khi xoá) ---");
//        userManager.findAll();
//
//        // ----- 7. TEST DELETE (ID không tồn tại) -----
//        System.out.println("\n--- 7. ĐANG TEST DELETE (ID không tồn tại) ---");
//        userManager.deleteById("ID_SAI_KHONG_TON_TAI");
//
//        // ----- 8. TEST BÀI 3: Tìm users FPT không phải admin -----
//        System.out.println("\n--- 8. ĐANG TEST BÀI 3: TÌM USERS FPT (NOT ADMIN) ---");
//        userManager.findFptUsersNotAdmin();
//
//        // ----- 9. TEST BÀI 4: Phân trang - Trang 3, mỗi trang 5 users -----
//        System.out.println("\n--- 9. ĐANG TEST BÀI 4: PHÂN TRANG (TRANG 3, 5 USERS/TRANG) ---");
//        userManager.findUsersByPage(3, 5);
//
//        System.out.println("\n--- TEST HOÀN TẤT ---");
//    }
//}