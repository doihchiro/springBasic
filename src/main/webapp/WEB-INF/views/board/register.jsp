<%--
  Created by IntelliJ IDEA.
  User: l3lur
  Date: 2024-09-06
  Time: 오후 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp"%>
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Register</h1>
<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
    For more information about DataTables, please visit the <a target="_blank"
                                                               href="https://datatables.net">official DataTables documentation</a>.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Board Register</h6>
    </div>
    <div class="card-body">
        <form id="registerForm" action="/board/register" method="post" enctype="multipart/form-data">
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Title</span>
                </div>
                <input type="text" name="title" class="form-control" >
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Content</span>
                </div>
                <textarea class="form-control" name="content" rows="3"></textarea>
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Writer</span>
                </div>
                <input type="text" name="writer" class="form-control" >
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Files</span>
                </div>
                <input type="file" name="files" class="form-control" multiple>
            </div>
            <div class="input-group input-group-lg">
                <button type="submit" class="btn btn-primary submitBtn" >SUBMIT</button>
            </div>
        </form>
    </div>
</div>



<%@include file="../includes/footer.jsp"%>

<script>

    const registerForm = document.querySelector("#registerForm");

    document.querySelector(".submitBtn").addEventListener('click', (e) => {
        e.preventDefault()
        e.stopPropagation()

        // JavaScript 에서 정규식을 리터럴 방식으로 정의
        const fileReg = /(.*?)\.(gif|png|jpg|jpeg|bmp)$/

        const fileInput = document.querySelector("input[name='files']");

        //console.dir(fileInput)
        const fileArr = fileInput.files;

        if (fileArr && fileArr.length > 0) {
            for (const file of fileArr) {
                //console.dir(file)
               if(!file.name.match(fileReg)) {
                   alert("허용하지 않는 파일 확장자")
                   return
               }
            }
        }

        registerForm.submit()

    }, false)

</script>

<%@include file="../includes/end.jsp"%>
