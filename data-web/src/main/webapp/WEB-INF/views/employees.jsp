<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link href="/css/styles.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <h1 class="page-header">
        Employees
    </h1>
    <div class="container-fluid controls-bar">
        <div class="row">
            <div class="text-right">
                <a href="/employee/edit" class="btn btn-default">New</a>
            </div>
        </div>
    </div>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Gender</th>
            <th>Birth Date</th>
            <th>Hire Date</th>
            <th>Department</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
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
        </tbody>
    </table>
</div>
</body>
