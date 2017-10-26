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

    <table class="table table-bordered" >
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
        <tbody id="employeeData">
            <tr><td colspan="6" align="center">Loading ...</td></tr>
        </tbody>
    </table>
</div>

<script
        src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>

<script>
    $.ajax('/employee').done(function(data) {
        $('#employeeData').html(data);
    });
</script>
</body>
</html>