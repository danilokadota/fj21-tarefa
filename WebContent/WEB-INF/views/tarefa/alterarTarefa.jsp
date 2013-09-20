<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Tarefa</title>
</head>
<body>
	<h3>Alterar Tarefas</h3>
	<form action="alteraTarefa" method = "post">
	    <input type="hidden" name="id" value="${tarefa.id}"/>
		Descrição:<br />
		<textarea name="descricao" rows ="5" cols="100">${tarefa.descricao}</textarea><br />

		<input type="submit" value="alterar">
	</form>
</body>
</html>