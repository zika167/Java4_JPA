//package com.wangquocthai.java4.lab1;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.TypedQuery;
//
//import java.util.List;
//
//public class UserManager {
//
//    // 1. Tạo factory MỘT LẦN DUY NHẤT, dùng chung cho toàn bộ ứng dụng
//    private static EntityManagerFactory factory;
//
//    // Sử dụng static block để khởi tạo factory một cách an toàn
//    static {
//        try {
//            factory = Persistence.createEntityManagerFactory("Java4_Lab1_PolyOE");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khởi tạo EntityManagerFactory!");
//        }
//    }
//
//    /**
//     * Hàm CREATE: Thêm một User mới vào CSDL
//     * @param newUser Đối tượng User (chưa có trong CSDL)
//     */
//    public void create(User newUser) {
//        EntityManager em = null; // 2. Tạo EM mới
//        try {
//            em = factory.createEntityManager();
//
//            // 3. Bắt đầu Transaction
//            em.getTransaction().begin();
//
//            // 4. Dùng persist() để lưu đối tượng mới
//            em.persist(newUser);
//
//            // 5. Chấp nhận thay đổi
//            em.getTransaction().commit();
//            System.out.println("Tạo user thành công: " + newUser.getId());
//
//        } catch (Exception e) {
//            System.err.println("Lỗi khi tạo user: " + e.getMessage());
//            if (em != null && em.getTransaction().isActive()) {
//                em.getTransaction().rollback(); // 6. Hoàn tác nếu có lỗi
//            }
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close(); // 7. Luôn đóng EM
//            }
//        }
//    }
//
//    /**
//     * Hàm UPDATE: Cập nhật thông tin một User đã có
//     * @param detachedUser Đối tượng User đã bị thay đổi thông tin
//     */
//    public void update(User detachedUser) {
//        EntityManager em = null;
//        try {
//            em = factory.createEntityManager();
//            em.getTransaction().begin();
//
//            // 1. Dùng merge() để cập nhật đối tượng đã có
//            // merge() sẽ tìm user có cùng ID và cập nhật các trường
//            em.merge(detachedUser);
//
//            em.getTransaction().commit();
//            System.out.println("Cập nhật user thành công: " + detachedUser.getId());
//
//        } catch (Exception e) {
//            System.err.println("Lỗi khi cập nhật user: " + e.getMessage());
//            if (em != null && em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    /**
//     * Hàm DELETE: Xoá một User khỏi CSDL bằng ID
//     * @param userId ID của user cần xoá
//     */
//    public void deleteById(String userId) {
//        EntityManager em = null;
//        try {
//            em = factory.createEntityManager();
//            em.getTransaction().begin();
//
//            // 1. Phải TÌM user trước khi xoá
//            User user = em.find(User.class, userId);
//
//            // 2. XỬ LÝ NULL: Rất quan trọng!
//            if (user != null) {
//                // 3. Dùng remove() để xoá đối tượng
//                em.remove(user);
//                System.out.println("Xoá user thành công: " + userId);
//            } else {
//                System.out.println("Không thể xoá, user không tồn tại: " + userId);
//            }
//
//            // 4. Commit (kể cả khi không tìm thấy user, vẫn commit để kết thúc transaction)
//            em.getTransaction().commit();
//
//        } catch (Exception e) {
//            System.err.println("Lỗi khi xoá user: " + e.getMessage());
//            if (em != null && em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    // --- Các hàm Read (Find) chúng ta đã làm ---
//    public void findAll() {
//        EntityManager em = null;
//        try {
//            em = factory.createEntityManager();
//            String jpql = "select u from User u"; // Dùng tên Entity "User"
//            TypedQuery<User> query = em.createQuery(jpql, User.class);
//            List<User> users = query.getResultList();
//            System.out.println("--- DANH SÁCH TẤT CẢ USER ---");
//            users.forEach(user -> {
//                System.out.println("ID: " + user.getId() + " | Tên: " + user.getFullname()
//                        + " | Email: " + user.getEmail() + " | Admin: " + user.isAdmin());
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public User findById(String userId) {
//        EntityManager em = null;
//        User user = null;
//        try {
//            em = factory.createEntityManager();
//            user = em.find(User.class, userId);
//
//            if (user != null) {
//                System.out.println("--- TÌM THẤY USER: " + userId + " ---");
//                System.out.println("ID: " + user.getId() + " | Tên: " + user.getFullname()
//                        + " | Email: " + user.getEmail() + " | Admin: " + user.isAdmin());
//            } else {
//                System.out.println("!!! KHÔNG TÌM THẤY user với ID: " + userId);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return user; // Trả về user (hoặc null)
//    }
//
//    /**
//     * BÀI 3: Tìm kiếm users có email kết thúc bởi "@fpt.edu.vn" và không phải admin
//     * Sử dụng JPQL với LIKE và tham số hóa
//     */
//    public void findFptUsersNotAdmin() {
//        EntityManager em = null;
//        try {
//            em = factory.createEntityManager();
//
//            // JPQL query với LIKE để tìm email chứa "@fpt.edu.vn"
//            String jpql = "SELECT u FROM User u WHERE u.email LIKE :search AND u.admin = :role";
//            TypedQuery<User> query = em.createQuery(jpql, User.class);
//
//            // Sử dụng setParameter() để truyền tham số an toàn
//            query.setParameter("search", "%@fpt.edu.vn");
//            query.setParameter("role", false);
//            List<User> users = query.getResultList();
//
//            System.out.println("--- DANH SÁCH USER FPT.EDU.VN (NOT ADMIN) ---");
//            if (users.isEmpty()) {
//                System.out.println("Không tìm thấy user nào!");
//            } else {
//                users.forEach(user -> {
//                    System.out.println("ID: " + user.getId() + " | Tên: " + user.getFullname()
//                        + " | Email: " + user.getEmail());
//                });
//            }
//        } catch (Exception e) {
//            System.err.println("Lỗi khi tìm users FPT: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    /**
//     * BÀI 4: Phân trang - lấy users từ trang thứ N
//     * Sử dụng setFirstResult() và setMaxResults()
//     * @param pageNumber Số trang (bắt đầu từ 1)
//     * @param pageSize Số lượng users trên mỗi trang
//     */
//    public void findUsersByPage(int pageNumber, int pageSize) {
//        EntityManager em = null;
//        try {
//            em = factory.createEntityManager();
//
//            // JPQL query để lấy tất cả users
//            String jpql = "SELECT u FROM User u ORDER BY u.id";
//            TypedQuery<User> query = em.createQuery(jpql, User.class);
//
//            // Tính toán vị trí bắt đầu (offset)
//            // Trang 1: offset = 0, Trang 2: offset = 5, Trang 3: offset = 10, ...
//            int offset = (pageNumber - 1) * pageSize;
//
//            // Sử dụng setFirstResult() để chỉ định vị trí bắt đầu
//            query.setFirstResult(offset);
//
//            // Sử dụng setMaxResults() để chỉ định số lượng kết quả
//            query.setMaxResults(pageSize);
//
//            List<User> users = query.getResultList();
//
//            System.out.println("--- DANH SÁCH USERS TRANG " + pageNumber + " (Mỗi trang " + pageSize + " users) ---");
//            if (users.isEmpty()) {
//                System.out.println("Không có users trên trang này!");
//            } else {
//                users.forEach(user -> {
//                    System.out.println("ID: " + user.getId() + " | Tên: " + user.getFullname()
//                        + " | Email: " + user.getEmail() + " | Admin: " + user.isAdmin());
//                });
//            }
//        } catch (Exception e) {
//            System.err.println("Lỗi khi lấy users theo trang: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//}