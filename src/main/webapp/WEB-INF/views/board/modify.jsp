<%--
  Created by IntelliJ IDEA.
  User: l3lur
  Date: 2024-09-06
  Time: 오후 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp"%>
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Modify</h1>
<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
    For more information about DataTables, please visit the <a target="_blank"
                                                               href="https://datatables.net">official DataTables documentation</a>.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Board Modify</h6>
    </div>
    <div class="card-body">
        <form id="actionForm" action="/board/modify" method="post" enctype="multipart/form-data">

            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Bno</span>
                </div>
                <input type="text" name="bno" class="form-control" value="<c:out value="${vo.bno}"/>"  readonly>
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Title</span>
                </div>
                <input type="text" name="title" class="form-control"  value="<c:out value="${vo.title}"/>">
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Content</span>
                </div>
                <textarea class="form-control"
                          name="content" rows="3"
                          contenteditable="false"><c:out value="${vo.content}"/></textarea>
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Writer</span>
                </div>
                <input type="text" class="form-control"  value="<c:out value="${vo.writer}"/>"  readonly>
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">RegDate</span>
                </div>
                <input type="text" class="form-control"  value="<c:out value="${vo.regDate}"/>"  readonly>
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Files</span>
                </div>
                <input type="file" name="files" class="form-control" multiple>
            </div>

            <div class="float-right mt-2">
                <button type="button" class="btn btn-info btnList" >LIST</button>
                <button type="button" class="btn btn-warning btnModify" >MODIFY</button>
                <button type="button" class="btn btn-danger btnRemove" >REMOVE</button>
            </div>

            <div class="deleteImages"></div>
        </form>
    </div>
</div>

<div class="card">

    <div class="attachList d-flex justify-content-center">
        <c:if test="${vo.attachList != null && vo.attachList.size() > 0}">
            <c:forEach items="${vo.attachList}" var="attach">
                <c:if test="${attach.ano != null}">
                    <div class="d-flex m-1 position-relative">
                        <img src="/files/s_${attach.fullName}">
                        <button class="removeImgBtn btn btn-danger btn-sm position-absolute top-0 start-100 translate-middle-x"
                                data-ano="${attach.ano}"
                                data-fullname="${attach.fullName}"
                        >X</button>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
    </div>

</div>

<form id="listForm" method="get" action="/board/list">
    <input type="hidden" name="pageNum" value="${cri.pageNum}">
    <input type="hidden" name="amount" value="${cri.amount}">
    <c:if test="${cri.types != null && cri.keyword != null}">
        <c:forEach var="type" items="${cri.types}">
            <input type="hidden" name="types" value="${type}">
        </c:forEach>
        <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
    </c:if>
</form>

<%@include file="../includes/footer.jsp"%>

<script>

    const bno = ${vo.bno}
    const actionForm = document.querySelector('#actionForm')
    const listForm = document.querySelector("#listForm")

    document.querySelector('.btnList').addEventListener('click', (e) => {
        e.preventDefault()
        e.stopPropagation()
        listForm.submit()
    }, false)

    document.querySelector('.btnModify').addEventListener('click', (e) => {
        e.preventDefault()
        e.stopPropagation()

        actionForm.action = `/board/modify/\${bno}`
        actionForm.method = 'post'
        actionForm.submit()
    }, false)

    document.querySelector('.btnRemove').addEventListener('click', (e) => {
        e.preventDefault()
        e.stopPropagation()

        const buttons = document.querySelectorAll(".attachList button");

        console.log(buttons)

        if (buttons && buttons.length > 0) {

            let str = ''

            buttons.forEach(button => {
                const ano = button.dataset.ano
                const fullName = button.dataset.fullname;

                str += `<input type="hidden" name="anos" value="\${ano}">`
                str += `<input type="hidden" name="fullNames" value="\${fullName}">`
            })

            document.querySelector('.deleteImages').innerHTML += str
        }

        actionForm.action = `/board/remove/\${bno}`
        actionForm.method = 'post'
        actionForm.submit()

    }, false)

    document.querySelector(".attachList").addEventListener('click', (e) => {

        const target = e.target;

        if (target.tagName !== 'BUTTON') {
            return
        }

        const ano = target.dataset.ano
        const fullName = target.dataset.fullname

        if (ano && fullName) {

            let str = ''

            str += `<input type="hidden" name="anos" value="\${ano}">`
            str += `<input type="hidden" name="fullNames" value="\${fullName}">`

            target.closest('div').remove()

            document.querySelector('.deleteImages').innerHTML += str
        }

    }, false)
</script>

<%@include file="../includes/end.jsp"%>
