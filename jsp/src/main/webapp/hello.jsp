<h1> Hello from JSP with HelloServlet java class</h1>
Attribute from servlet:  ${attribute1} <br />

<p>
    <jsp:useBean id="instanceName" scope="request"
        class="java.util.Date">
    </jsp:useBean>

    <%-- TIPS: scope= "page | request | session | application" --%>

    <%
        long t = instanceName.getTime();
        out.print("Using Bean in JSP. Time in ms is " + t);
    %>
</p>