<%--
  Created by IntelliJ IDEA.
  User: iceman
  Date: 7/13/12
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
    <ul id="scroller">
        <li>

            One
        </li>
        <li>
            Two
        </li>
    </ul>
<body>

</body>

<script type="text/javascript">
    $(document).ready(function()
    {
        alert("gigi");
        $('#scroller').css('height', $('#scroller').height());
        $('#scroller li:first').css('margin-top', 0 - $('scroller li.first'). outerHeight());
        $('#scroller').css('overflow', 'hidden');
        var ulPaddaingTop = parseInt($('scroller').css('padding-top'));
        $('#scroller li:first').css('position', 'relative');
        $('#scroller li:first').css('top',0-ulPaddaingTop);
        $('#scroller li:first').animate({top: 0});


    });
</script>
</html>