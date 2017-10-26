<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="employee" items="${employees}">
    <tr>
        <td>${employee.name}</td>
        <td>${employee.gender}</td>
        <td>${employee.birthDate}</td>
        <td>${employee.hireDate}</td>
        <td>${employee.department.name}</td>
        <td class="controls">
            <a href="/employee/edit?id=${employee.id}" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            </a>
            <a href="/employee/delete?id=${employee.id}" class="btn btn-danger btn-sm">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </a>
        </td>
    </tr>
</c:forEach>