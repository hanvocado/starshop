<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Pagination -->
    <div class="row justify-content-center justify-content-sm-between align-items-sm-center">
        <div class="col-sm mb-2 mb-sm-0">
            <div class="d-flex justify-content-center justify-content-sm-start align-items-center">
                <span class="mr-2">Page size: </span>
                <!-- Select -->
                <form id="pageSizeForm" action="<c:url value='/${role}'/>" method="get">
                    <select name="pageSize" onchange="document.getElementById('pageSizeForm').submit();"
                        id="datatableEntries" class="js-select2-custom" data-hs-select2-options='{
		                            "minimumResultsForSearch": "Infinity",
		                            "customClass": "custom-select custom-select-sm custom-select-borderless",
		                            "dropdownAutoWidth": true,
		                            "width": true
		                          }'>
                        <option value="9" <c:if test="${pageSize == 9}">selected</c:if>>9</option>
                        <option value="12" <c:if test="${pageSize == 12}">selected</c:if>>12</option>
                        <option value="21" <c:if test="${pageSize == 18}">selected</c:if>>21</option>
                    </select>
                </form>

                <!-- End Select -->
            </div>
        </div>

        <div class="col-sm-auto">
            <div class="d-flex justify-content-center justify-content-sm-end">
                <!-- footer Pagination -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${isFirst }">
                            <li class="page-item disabled">
                        </c:if>
                        <c:if test="${!isFirst }">
                            <li class="page-item">
                        </c:if>
                        <a class="page-link" href="<c:url value='/?pageNo=${pageNo-1}' />" aria-label="Previous">
                            <span aria-hidden="true">«</span>
                            <span class="sr-only">Previous</span>
                        </a>
                        </li>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item <c:if test=" ${pageNo==i-1}">active</c:if>">
                                <a class="page-link" href="<c:url value='/?pageNo=${i-1}' />">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${isLast }">
                            <li class="page-item disabled">
                        </c:if>
                        <c:if test="${!isLast }">
                            <li class="page-item">
                        </c:if>
                        <a class="page-link" href="<c:url value='/?pageNo=${pageNo+1}' />" aria-label="Next">
                            <span aria-hidden="true">»</span>
                            <span class="sr-only">Next</span>
                        </a>
                        </li>
                    </ul>
                </nav>
                <!-- End footer Pagination -->
            </div>
        </div>
    </div>
    <!-- End Pagination -->