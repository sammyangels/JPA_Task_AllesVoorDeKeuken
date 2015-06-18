<%--@elvariable id="artikel" type="be.vdab.entities.artikel"--%>
<%--@elvariable id="fouten" type="be.vdab.servlets.artikels.ZoekenOpNummerServlet"--%>

<%@page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@taglib prefix='v' uri='http://vdab.be/tags' %>
<!doctype html>
<html lang="nl">
<head>
    <v:head title='${empty artikel ? "Artikel zoeken op nummer" : artikel.naam}'/>
</head>
<body>
    <v:menu/>
    <h1>Artikel zoeken op nummer</h1>
    <form>
        <label>Nummer:<span>${fouten.id}</span>
        <input name="id" value="${param.id}" required autofocus type="number" min="1">
        </label>
        <input type="submit" value="Zoeken">
    </form>
    <c:if test="${not empty param and empty fouten and empty artikel}">
        Artikel niet gevonden
    </c:if>
    <c:if test="${not empty artikel}">
        <dl>
            <dt>Naam</dt>
            <dd>${artikel.naam}</dd>
            <dt>Aankoopprijs</dt>
            <dd><fmt:formatNumber value='${artikel.aankoopprijs}'/></dd>
            <dt>Verkoopprijs</dt>
            <dd><fmt:formatNumber value='${artikel.verkoopprijs}'/></dd>
            <dt>Winst</dt>
            <dd><fmt:formatNumber value='${artikel.winst}'/>%</dd>
        </dl>
    </c:if>
</body>
</html>