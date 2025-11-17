package com.wangquocthai.java4.lab2.servlet;

import com.wangquocthai.java4.lab2.dao.UserDAO;
import com.wangquocthai.java4.lab2.dao.impl.UserDAOImpl;
import com.wangquocthai.java4.lab2.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet({
        "/user/crud/index",
        "/user/crud/edit/*",
        "/user/crud/create",
        "/user/crud/update",
        "/user/crud/delete",
        "/user/crud/reset"
})
public class UserCRUDServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User form = new User();

        // yeu cau 1:
        UserDAO dao = new UserDAOImpl();
        try {
            // Lấy dữ liệu từ form (như id, fullname, email...)
            BeanUtils.populate(form, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        String message = "Enter user information";
        String path = request.getServletPath();

        if (path.contains("edit")) {
            String id = request.getPathInfo().substring(1);
            message = "Edit: " + id;

            // yeu cau 2:
            form = dao.findById(id);

        } else if (path.contains("create")) {
            // yeu cau 3:
            dao.create(form);

            // yeu cau 7:
            message = "Create: " + form.getId() + "successful!";
            form = new User();

        } else if (path.contains("update")) {
            // yeu cau 4:
            dao.update(form);

            // yeu cau 7:
            message = "Update: " + form.getId() + "successful!";

        } else if (path.contains("delete")) {
            // yeu cau 5:
            dao.deleteById(form.getId());

            // yeu cau 7:
            message = "Delete: " + form.getId() + "successful!";
            form = new User();

        } else if (path.contains("reset")) {
            form = new User();
            message = "Form has been reset.";
        }
        List<User> list = dao.findAll();

        request.setAttribute("message", message);
        request.setAttribute("user", form);
        request.setAttribute("users", list);
        request.getRequestDispatcher("/lab2/views/user-crud.jsp").forward(request, response);
    }
}