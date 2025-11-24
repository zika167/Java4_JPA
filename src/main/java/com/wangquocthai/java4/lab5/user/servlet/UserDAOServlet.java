package com.wangquocthai.java4.lab5.user.servlet;

import com.wangquocthai.java4.lab4.user.dao.UserDAO;
import com.wangquocthai.java4.lab4.user.dao.UserDAOImpl;
import com.wangquocthai.java4.lab4.user.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet({
                "/lab5-user/crud/index",
                "/lab5-user/crud/edit/*",
                "/lab5-user/crud/create",
                "/lab5-user/crud/update",
                "/lab5-user/crud/delete",
                "/lab5-user/crud/reset"
})
public class UserDAOServlet extends HttpServlet {
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                UserDAO user = new UserDAOImpl();
                User form = new User();
                try {
                        BeanUtils.populate(form, req.getParameterMap());
                } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                }
                String message = "Enter user information";
                String path = req.getServletPath();
                if (path.contains("edit")) {
                        String id = req.getPathInfo().substring(1);
                        message = "Edit: " + id;
                        form = user.findById(id);
                } else if (path.contains("create")) {
                        message = "Create: " + form.getId();
                        user.create(form);
                } else if (path.contains("update")) {
                        User existingUser = user.findById(form.getId());
                        if (existingUser != null) {
                                existingUser.setFullname(form.getFullname());
                                existingUser.setEmail(form.getEmail());
                                existingUser.setPassword(form.getPassword());
                                existingUser.setAdmin(form.getAdmin());
                                user.update(existingUser);
                                message = "Update: " + form.getId();
                        }
                } else if (path.contains("delete")) {
                        message = "Delete: " + form.getId();
                        user.deleteById(form.getId());
                } else if (path.contains("reset")) {
                        form = new User();
                }
                List<User> list = user.findAll();

                req.setAttribute("message", message);
                req.setAttribute("user", form);
                req.setAttribute("users", list);
                req.getRequestDispatcher("/lab4/views/user/user_crud.jsp").forward(req, resp);
        }
}
