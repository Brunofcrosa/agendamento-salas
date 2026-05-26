<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/crud.css?v=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sidebar.css?v=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <title>Docentes</title>
</head>
<body>
<div class="page-wrapper">
    <c:set var="pageName" value="docentes" scope="request" />
    <jsp:include page="/WEB-INF/pages/includes/navbar.jsp" />
    <div class="app-shell">
        <jsp:include page="/WEB-INF/pages/includes/sidebar.jsp" />
        <main class="main-content">
            <c:if test="${not empty erro}"><div class="msg erro">${erro}</div></c:if>
            <c:choose>
                <c:when test="${param.msg == 'salvo'}"><div class="msg">Docente cadastrado!</div></c:when>
                <c:when test="${param.msg == 'excluido'}"><div class="msg">Docente excluído!</div></c:when>
                <c:when test="${param.msg == 'editado'}"><div class="msg">Docente atualizado!</div></c:when>
            </c:choose>
            <c:if test="${tela == 'novo'}">
            <section class="content-card">
                <h2>Novo Docente</h2>
                <form action="docente" method="post" class="form-grid">
                    <input type="hidden" name="id" value="${docente.id}">
                    <div class="form-field">
                        <label>Nome do Docente</label>
                        <input type="text" name="nome" value="${docente.nome}" placeholder="Ex: Professor X" required>
                    </div>
                    <div class="form-field">
                        <label>E-mail</label>
                        <input type="email" name="email" value="${docente.email}" placeholder="professor@instituicao.edu" required>
                    </div>
                    <div class="form-field">
                        <label>Telefone</label>
                        <input type="text" name="telefone" value="${docente.telefone}" placeholder="(00) 00000-0000">
                    </div>
                    <div class="form-field">
                        <label>Departamento</label>
                        <input type="text" name="departamento" value="${docente.departamento}" placeholder="Ex: Matemática" required>
                    </div>
                    <label class="check"><input type="checkbox" name="ativo" ${docente.id == null || docente.ativo ? 'checked' : ''}> Ativo</label>
                    <div class="form-actions">
                        <button type="submit">${docente.id != null ? 'Atualizar Docente' : 'Cadastrar Docente'}</button>
                    </div>
                </form>
            </section>
            </c:if>
            <c:if test="${tela == 'listar'}">
            <section class="content-card">
                <div class="card-header-actions">
                    <h2>Docentes Cadastrados</h2>
                    <a class="btn-primary-link" href="docente?tela=novo">Cadastrar docente</a>
                </div>
                <table>
                    <tr><th>ID</th><th>Nome do Docente</th><th>E-mail</th><th>Departamento</th><th>Ações</th></tr>
                    <c:forEach var="d" items="${docentes}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${d.nome}</td>
                            <td>${d.email}</td>
                            <td>${d.departamento}</td>
                            <td class="actions"><a href="docente?acao=editar&id=${d.id}">Editar</a><a href="docente?acao=excluir&id=${d.id}" onclick="return confirm('Excluir docente?')">Excluir</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
            </c:if>
        </main>
    </div>
</div>
</body>
</html>

