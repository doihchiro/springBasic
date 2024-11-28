<%--
  Created by IntelliJ IDEA.
  User: l3lur
  Date: 2024-09-06
  Time: 오후 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../includes/header.jsp"%>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Tables</h1>
<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
    For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <div class="m-0 font-weight-bold text-primary">Board List</div>
        <sec:authorize access="isAuthenticated()">
            <div class="float-right">
                <a href="/board/register">
                    <button class="btn btn-info">Register</button>
                </a>
            </div>
        </sec:authorize>
    </div>
    <div class="card-body">

        <div class="float-right d-flex justify-content-start" style="margin-bottom: 2em">
            <select name="typeSelect" class="form-select form-control">
                <option value="">--</option>
                <option value="T" ${cri.typeStr == 'T' ? 'selected' : ''}>제목</option>
                <option value="C" ${cri.typeStr == 'C' ? 'selected' : ''}>내용</option>
                <option value="W" ${cri.typeStr == 'W' ? 'selected' : ''}>작성자</option>
                <option value="TC" ${cri.typeStr == 'TC' ? 'selected' : ''}>제목 OR 내용</option>
                <option value="TW" ${cri.typeStr == 'TW' ? 'selected' : ''}>제목 OR 작성자</option>
                <option value="TCW" ${cri.typeStr == 'TCW' ? 'selected' : ''}>제목 OR 내용 OR 작성자</option>
            </select>
            <input type="text" class="form-control" name="keywordInput" value="<c:out value="${cri.keyword}"/>">
            <button class="btn btn-default searchBtn">Search</button>
        </div>

        <div class="table-responsive">

        <%--${cri}--%>
        <%--${pageMaker}--%>

            <form id="actionForm" method="get">
                <input type="hidden" name="pageNum" value="${cri.pageNum}">
                <input type="hidden" name="amount" value="${cri.amount}">
                <c:if test="${cri.types != null && cri.keyword != null}">
                    <c:forEach var="type" items="${cri.types}">
                        <input type="hidden" name="types" value="${type}">
                    </c:forEach>
                    <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
                </c:if>
            </form>

            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Bno</th>
                    <th>Title</th>
                    <th>Writer</th>
                    <th>RegDate</th>
                    <th>UpdateDate</th>
                </tr>
                </thead>

                <tbody class="tbody">
                <c:forEach var="board" items="${list}">
                    <tr data-bno = "${board.bno}">
                        <td><c:out value="${board.bno}"/></td>
                        <td><c:out value="${board.title}"/></td>
                        <td><c:out value="${board.writer}"/></td>
                        <td><c:out value="${board.regDate}"/></td>
                        <td><c:out value="${board.updateDate}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div>
                <ul class="pagination justify-content-center">

                    <c:if test="${pageMaker.prev}">
                    <li class="page-item">
                        <a class="page-link" href="${pageMaker.startPage - 1}" tabindex="-1">Prev</a>
                    </li>
                    </c:if>

                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
                    <li class="page-item ${cri.pageNum == num ? 'active' : ''}">
                        <a class="page-link" href="${num}">${num}</a>
                    </li>
                    </c:forEach>

                    <c:if test="${pageMaker.next}">
                    <li class="page-item">
                        <a class="page-link" href="${pageMaker.endPage + 1}">Next</a>
                    </li>
                    </c:if>

                </ul>
            </div>
        </div>
    </div>
</div>

<div id="myModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>${result}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Save changes</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<%@include file="../includes/footer.jsp"%>

<script>

    const result = '${result}';

    const myModal = new bootstrap.Modal(document.getElementById('myModal'))

    //console.log(myModal)

    if (result) {
        myModal.show()
    }

    const actionForm = document.querySelector("#actionForm");

    document.querySelector(".tbody").addEventListener('click', (e) => {

        const target = e.target.closest('tr')
        //console.log(target)

        // 'thead' 안에 있는 'tr' 클릭 시 중단
        // if (!target || !target.closest('tbody')) {
        //     return;  // 이벤트 중단
        // }

        const bno = target.dataset.bno
        console.log(bno)

        const before = document.querySelector("#clonedActionForm");
        if (before) before.remove()

        const clonedActionForm = actionForm.cloneNode(true)
        clonedActionForm.setAttribute("action", `/board/read/\${bno}`)
        clonedActionForm.setAttribute("id", "clonedActionForm")
        document.body.appendChild(clonedActionForm)
        clonedActionForm.submit();

        console.log(clonedActionForm);

        //window.location = `/board/read/\${bno}`

    }, false)

    document.querySelector(".pagination").addEventListener('click', (e) => {

        e.preventDefault() // a tag 기본 동작 방지
        // e.stopPropagation(); // 버블링 멈춤
        const target = e.target;
        console.log(target);

        // `a` 태그가 아닐 경우 리턴
        if (target.tagName !== 'A') return;

        const targetPage = target.getAttribute("href");
        console.log(targetPage)

        // window.location = `/board/list?pageNum=\${targetPage}`
        actionForm.setAttribute("action", "/board/list");
        actionForm.querySelector("input[name='pageNum']").value = targetPage;
        actionForm.submit();

    }, false);

    document.querySelector(".searchBtn").addEventListener('click', (e) => {
        e.preventDefault();
        e.stopPropagation();

        //name 속성이 typeSelect 인 <select> 요소를 선택
        const selectObj = document.querySelector("select[name='typeSelect']");
        console.log(selectObj);

        //selectObj.options:
        //<select> 요소의 모든 <option> 요소들을 배열처럼 접근할 수 있다.
        //selectObj.selectedIndex:
        //현재 선택된 <option>의 인덱스 번호를 반환한다.
        //예를 들어, 첫 번째 옵션이 선택되었으면 0, 두 번째 옵션이면 1
        //.value:
        //선택된 <option>의 value 속성 값을 가져옵니다.
        //예시: <option value="T">Title</option>에서 "T"를 가져온다.
        const selectValue = selectObj.options[selectObj.selectedIndex].value;
        console.log(selectValue);

        //selectValue.split(""):
        //선택된 값인 selectValue 문자열을 빈 문자열 기준으로 나누어 배열로 변환한다.
        //이 경우, 각 문자를 하나씩 분리하여 배열의 요소로 만든다.
        //const arr = "TC".split("");
        //console.log(arr); // ["T", "C"]
        const arr = selectValue.split("");
        console.log(arr);

        let str = '';

        str = `<input type="hidden" name="pageNum" value=1>`
        str += `<input type="hidden" name="amount" value=${cri.amount}>`

        if (arr && arr.length > 0) {
            for (const type of arr) {
                str += `<input type="hidden" name="types" value=\${type}>`
            }
        }

        const keywordValue = document.querySelector("input[name='keywordInput']").value;
        str += `<input type="hidden" name="keyword" value=\${keywordValue}>`

        actionForm.innerHTML = str;

        console.log(str);

        actionForm.submit();
    }, false);

</script>

<%@include file="../includes/end.jsp"%>