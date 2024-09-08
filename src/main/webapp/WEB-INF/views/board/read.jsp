<%--
  Created by IntelliJ IDEA.
  User: l3lur
  Date: 2024-09-06
  Time: 오후 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp"%>
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Read</h1>
<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
    For more information about DataTables, please visit the <a target="_blank"
                                                               href="https://datatables.net">official DataTables documentation</a>.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Board Read</h6>
    </div>
    <div class="card-body">
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Bno</span>
            </div>
            <input type="text" class="form-control" value="<c:out value="${vo.bno}"/>"  readonly>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Title</span>
            </div>
            <input type="text" name="title" class="form-control"  value="<c:out value="${vo.title}"/>"  readonly >
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Content</span>
            </div>
            <textarea class="form-control"
                      name="content" rows="3"
                      contenteditable="false"
                      readonly><c:out value="${vo.content}"/></textarea>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Writer</span>
            </div>
            <input type="text" name="writer" class="form-control"  value="<c:out value="${vo.writer}"/>"  readonly>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">RegDate</span>
            </div>
            <input type="text" name="writer" class="form-control"  value="<c:out value="${vo.regDate}"/>"  readonly>
        </div>
        <div class="float-right">
            <button type="button" class="btn btn-info btnList" >LIST</button>

            <%--<c:if test="${!vo.delFlag}">--%>
                <button type="button" class="btn btn-warning btnModify" >MODIFY</button>
            <%--</c:if>--%>
        </div>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>

<script>
    document.querySelector('.btnList').addEventListener('click', (e) => {
        window.location = '/board/list'
    }, false)

    document.querySelector('.btnModify').addEventListener('click', (e) => {
        window.location = '/board/modify/${vo.bno}'
    }, false)
</script>

<%@include file="../includes/end.jsp"%>
