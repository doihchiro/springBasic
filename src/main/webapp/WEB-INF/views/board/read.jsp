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

<div class="card shadow mb-4">

    <div class="attachList d-flex justify-content-center">
        <c:if test="${vo.attachList != null && vo.attachList.size() > 0}">
            <c:forEach items="${vo.attachList}" var="attach">
                <c:if test="${attach.ano != null}">
                    <div class="m-1">
                        <a href="/files/${attach.fullName}" target="_blank">
                            <img src="/files/s_${attach.fullName}"/>
                        </a>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
    </div>

</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <button class="btn btn-info addCommentBtn">Add Comment</button>
    </div>
    <div class="card-body ">
        <div>
            <ul class="list-group comments">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    Cras justo odio
                    <span class="badge badge-primary badge-pill">14</span>
                </li>
            </ul>
        </div>
        <div class="mt-3" >
            <ul class="pagination d-flex justify-content-center">
                <li class="page-item ">
                    <a class="page-link" href="#" tabindex="-1">Prev</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active">
                    <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                </li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="modal" id="commentModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body ">
                <div class="input-group input-group-lg">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Comment Text</span>
                    </div>
                    <input type="text" name="commentText" class="form-control" >
                </div>
                <div class="input-group input-group-sm">
                    <div class="input-group-prepend">
                        <span class="input-group-text">commenter</span>
                    </div>
                    <input type="text" name="commenter" class="form-control" >
                </div>
            </div>
            <div class="modal-footer">
                <button id='commentModBtn' type="button" class="btn btn-warning">Modify</button>
                <button id='commentDelBtn' type="button" class="btn btn-danger">Delete</button>
                <button id='commentRegBtn' type="button" class="btn btn-primary">Register</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

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

<%@include file="../includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>

    const actionForm = document.querySelector("#actionForm");
    const bno = ${vo.bno};

    document.querySelector('.btnList').addEventListener('click', (e) => {
        actionForm.setAttribute("action", "/board/list")
        actionForm.submit()
    }, false)

    document.querySelector('.btnModify').addEventListener('click', (e) => {
        actionForm.setAttribute("action", `/board/modify/\${bno}`)
        actionForm.submit()
    }, false)

</script>

<script>

    const boardBno = ${vo.bno}
    const commentUL = document.querySelector('.comments')
    const pageUL = document.querySelector('.pagination')

    // 현재 댓글 페이지
    let currentPage = 1
    let currentRno = 0

    const getList = async (pageParam, amountParam) => {

        const pageNum = pageParam || 1
        const amount = amountParam || 10

        const res = await axios.get(`/comment/list/\${boardBno}`, {
            params: {pageNum, amount}
        })
        const data = res.data;
        const comments = data.comments;
        const pageDTO = data.pageDTO;

        printComments(comments, pageDTO)

        console.log(data)
        console.log(comments)
        console.log(pageDTO)
    }

    const registerComment = async (commentObj) => {

        const res = await axios.post(`/comment/register`, commentObj)
        const data = res.data

        const lastPage = Math.ceil(data.COUNT / 10.0)

        getList(lastPage)
    }

    const printComments = (comments, pageDTO) => {
        commentUL.innerHTML = ''

        let str = ''

        comments.forEach(comment => {

            const {rno, commentText, commenter} = comment

            str +=
                `<li data-rno="\${rno}" class="list-group-item d-flex justify-content-between align-items-center">
                    \${rno} --- \${commentText}
                    <span class="badge badge-primary badge-pill">\${commenter}</span>
                </li>`
        })

        commentUL.innerHTML = str

        //----------------------------------------
        const {startPage, endPage, prev, next} = pageDTO
        const pageNum = pageDTO.cri.pageNum

        pageUL.innerHTML = ''

        let pageStr = ''

        if (prev) {
            pageStr += `
                <li class="page-item ">
                    <a class="page-link" href="\${startPage - 1}" tabindex="-1">Prev</a>
                </li>
            `
        }

        for (let i = startPage; i <= endPage; i++) {
            pageStr += `<li class="page-item \${i === pageNum ? 'active' : ''}"><a class="page-link" href="\${i}">\${i}</a></li>`
        }

        if (next) {
            pageStr += `
                <li class="page-item ">
                    <a class="page-link" href="\${endPage + 1}" tabindex="-1">Next</a>
                </li>
            `
        }

        pageUL.innerHTML = pageStr
    }

    pageUL.addEventListener('click', (e) => {
        e.preventDefault()
        e.stopPropagation()

        const target = e.target
        console.log(target)
        // `a` 태그가 아닐 경우 리턴
        if (target.tagName !== 'A') return;

        const pageNum = target.getAttribute('href')
        console.log(pageNum)

        currentPage = pageNum

        getList(pageNum)

    }, false)

    commentUL.addEventListener('click', (e) => {
        e.preventDefault()
        e.stopPropagation()

        const target = e.target
        console.log(target)

        // 'LI' 태그가 아니면 함수 종료
        if (target.tagName !== 'LI') return

        currentRno = target.dataset.rno

        if (currentRno) console.log("currentRno: " + currentRno)

        console.log("currentPage: " + currentPage)

        getComment(currentRno).then(result => {
            commentTextInput.value = result.commentText
            commenterInput.value = result.commenter
            commentAddModal.show()
        })

    }, false)

    const getComment = async (rno) => {
        const res = await axios.get(`/comment/\${rno}`)
        console.log(res)
        return res.data
    }

    const deleteComment = async (rno) => {
        const res = await axios.delete(`/comment/\${rno}`)
        return res.data
    }

    const modifyComment = async (commentObj) => {
        const res = await axios.put(`/comment/\${currentRno}`, commentObj)
        return res.data
    }

    getList()

    // modal
    const commentAddModal = new bootstrap.Modal(document.querySelector('#commentModal'))
    const commentTextInput = document.querySelector("input[name='commentText']");
    const commenterInput = document.querySelector("input[name='commenter']");

    //commentAddModal.show()

    document.querySelector("#commentRegBtn").addEventListener('click', (e) => {
        e.preventDefault()
        e.stopPropagation()

        const commentObj = {
            bno: boardBno,
            commentText: commentTextInput.value,
            commenter: commenterInput.value
        }

        registerComment(commentObj).then(() => {
            commentAddModal.hide()
        })

    }, false)

    document.querySelector("#commentDelBtn").addEventListener('click', (e) => {
        deleteComment(currentRno).then(result => {
            alert("댓글이 삭제되었습니다.")
            commentAddModal.hide()
            getList()
        })
    }, false)

    document.querySelector("#commentModBtn").addEventListener('click', (e) => {

        const commentObj = {
            commentText: commentTextInput.value,
            commenter: commenterInput.value,
            bno: boardBno
        }

        modifyComment(commentObj).then(result => {
            alert("댓글이 수정되었습니다.")
            commentAddModal.hide()
            getList(currentPage)
        })
    }, false)

    document.querySelector(".addCommentBtn").addEventListener("click", e => {

        commentAddModal.show()

    }, false)

</script>

<%@include file="../includes/end.jsp"%>
