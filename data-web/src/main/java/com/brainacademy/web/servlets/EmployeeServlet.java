package com.brainacademy.web.servlets;

import com.brainacademy.service.dao.DaoFactory;
import com.brainacademy.service.dao.DepartmentDao;
import com.brainacademy.service.dao.EmployeeDao;
import com.brainacademy.service.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    private static final String USER_PAGE = "/WEB-INF/views/employees.jsp";
    private static final String USER_EDIT_PAGE = "/WEB-INF/views/edit.jsp";

    private EmployeeDao employeeDao;
    private DepartmentDao departmentDao;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeDao = DaoFactory.getInstance().createDao(EmployeeDao.class);
        departmentDao = DaoFactory.getInstance().createDao(DepartmentDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getPathInfo();
        RequestDispatcher dispatcher;
        if (path != null && path.endsWith("edit")) {
            dispatcher = this.getServletContext().getRequestDispatcher(
                    USER_EDIT_PAGE);
            String idParam = req.getParameter("id");
            Employee employee;
            if (idParam != null) {
                employee = employeeDao.getOne(Integer.valueOf(idParam));
            } else {
                employee = new Employee();
            }
            req.setAttribute("departments", departmentDao.getAll());
            req.setAttribute("employee", employee);
        } else if (path != null && path.endsWith("delete")) {
            Employee employee = employeeDao.getOne(NumberUtils.toInt(req.getParameter("id"), 0));
            if (employee != null) {
                employeeDao.delete(employee);
            }
            resp.sendRedirect("/employee");
            return;
        } else {
            dispatcher = this.getServletContext().getRequestDispatcher(USER_PAGE);
        }

        Integer page = NumberUtils.toInt(
                req.getParameter("page"), 1);


        List<Employee> employees = employeeDao.getAll(page);
        req.setAttribute("totalPage",
                (int) Math.floor(employeeDao.count()/10));

        req.setAttribute("page", page);
        req.setAttribute("employees", employees);

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = employeeDao.getOne(NumberUtils.toInt(req.getParameter("id"), 0));
        if (employee != null) {
            employeeDao.delete(employee);
        }
        resp.getWriter().print("OK");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String birthDateParam = req.getParameter("birthDate");
        String hireDateParam = req.getParameter("hireDate");
        Integer departmentId = NumberUtils.toInt(req.getParameter("departmentId"), 0);
        String gender = req.getParameter("gender");
        Integer id = NumberUtils.toInt(req.getParameter("id"), 0);

        Employee employee = (id > 0) ? employeeDao.getOne(id) : new Employee();
        employee.setName(name);
        employee.setGender(gender);
        employee.setDepartment((departmentId > 0) ? departmentDao
                .getOne(departmentId) : null);

        List<String> errors = new ArrayList<>();
        if (StringUtils.isEmpty(name)) {
            errors.add("The Employee Name cannot be empty");
        }

        if (departmentId == 0) {
            errors.add("Please select the department");
        }

        if (!"M".equals(gender) && !"F".equals(gender)) {
            errors.add("Please select the gender");
        }

        try {
            employee.setBirthDate(DateUtils.parseDate(
                    birthDateParam, "yyyy-MM-dd"));
        } catch (ParseException e) {
            errors.add("Incorrect the Birth Date");
        }

        try {
            employee.setHireDate(DateUtils.parseDate(hireDateParam, "yyyy-MM-dd"));
        } catch (ParseException e) {
            errors.add("Incorrect the Hire Date");
        }

        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.setAttribute("departments", departmentDao.getAll());
            req.setAttribute("employee", employee);
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher(USER_EDIT_PAGE);
            dispatcher.forward(req, resp);
        } else {
            if (id == 0) {
                employeeDao.create(employee);
            } else {
                employeeDao.update(employee);
            }
            resp.getWriter().print("OK");
        }
    }
}
