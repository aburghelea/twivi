<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Tviwi Engage</title>
</head>

<body>
<div class="rows">

    <div class="span6">
        <div class="well">
            <ul id="twitterInfoList" class="nav nav-pills nav-stacked">
                <li class="nav-header">Here resides the twitter Info</li>
            </ul>
        </div>
    </div>

    <div class="span8" id="accordion">
        <div id="info_panel" class="collapse in">
            <div id="infoDiv" class="alert alert-success">
                <button class="close" data-toggle="collapse" data-target="#info_panel">×</button>
                <button class="close" data-toggle="modal" href="#video_modal" onclick="requestVideo('gigi')">
                    <span class="icon-play"></span>
                </button>

                <strong>Here lies your intel!</strong>

                <div id="infoData">What do we say to the God of Death? ... Not today.</div>
            </div>


            <div class="modal hide fade" id="video_modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>

                    <h3>Video for my people</h3>
                </div>

                <div class="modal-body">
                    <div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div>

                    <div id="video_container" align="center" class="modal-body"></div>
                </div>
            </div>

        </div>
    </div>

</div>
</body>

</html>

<r:script>
    var elemsInList = 15;
    var newInfoFlag = true;
    var twitterInformation = new Array();

    var populateInfo = function () {
        var i;
        for (i = 0; i < elemsInList; i++) {
            twitterInformation.push("Info" + i);
        }
    };

    var requestVideo = function (token) {
        jQuery.ajax({type:'POST', data:{'token':token}, url:'/twivi/videoProducer/generateVideo',
            success:function (data, textStatus) {
                insertVideo(data);
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
            }});
    };

    var insertVideo = function (data) {
        $("#video_container").html(data);
    };

    var videoRequestLink = function (token) {
        return $('<a></a>', {
            href:"#video_modal",
            text:token,
            onClick:"requestVideo('" + token + "')",
            "data-toggle":"modal"
        });
    };

    var liTemplate = function (child) {
        return "<li>" + child + "</li>"
    };

    var twitterLink = function (caption) {
        return $('<a></a>', {
            href:"#info_panel",
            text:caption,
            onClick:'displayInfo(this)',
            class:"accordion-toggle",
            "data-toggle":'collapse',
            "data-target":'info_panel'
        });
    };

    var displayInfo = function (info) {
        $('#info_panel').collapse('show');
        var accumulator = "";
        var tokens = $(info).html().split(/[\s,@:#$%^&;()]+/);
        var link_divs = $("<div></div>");
        $.each(tokens, function (i, token) {
            $(link_divs).append(videoRequestLink(token));
            $(link_divs).append(" ");
        });
        $('#infoDiv #infoData').html($(link_divs));
    };

    var eraseOld = setInterval(function () {
        var howMany = twitterInformation.length - elemsInList;
        twitterInformation.splice(0, howMany);
    }, 5000);

    var updateList = setInterval(function () {
        var twList = $("#twitterInfoList");
        var last = twitterInformation.length;
        var i;
        if (newInfoFlag) {
            $(twList).fadeOut("slow", function () {
                $(twList).html("<li class='nav-header'>Here resides the twitter Info</li>");
                var idx = Math.max(0, last - elemsInList);
                for (i = idx; i < last; i++) {
                    $(twList).append($('<li></li>').append(twitterLink(twitterInformation[i])));
                }
            });
        }
        if (newInfoFlag) {
            $(twList).fadeIn("slow");
        }
        newInfoFlag = false;
    }, 1000);

    /** Compare if two arrays have the same elements (regardless of order) */
    var compareLists = function (arr1, arr2) {
        return $(arr1).not(arr2).length == 0 && $(arr2).not(arr1).length == 0
    };


    var poolResults = function () {
        <g:remoteFunction controller="twitterStream" action="update" onSuccess="insertResult(data)"/>
    };

    var startPooling = setInterval('poolResults()', 2500);

    var insertResult = function (result) {
        if (result === null || result === "" || result === "null" || result == undefined)
            return;
        newInfoFlag = true;
        twitterInformation.push(result);
    };
    var opts = {
        lines:9, // The number of lines to draw
        length:2, // The length of each line
        width:2, // The line thickness
        radius:7, // The radius of the inner circle
        rotate:2, // The rotation offset
        color:'#000', // #rgb or #rrggbb
        speed:1, // Rounds per second
        trail:34, // Afterglow percentage
        shadow:true, // Whether to render a shadow
        hwaccel:false, // Whether to use hardware acceleration
        className:'spinner', // The CSS class to assign to the spinner
        zIndex:2e9, // The z-index (defaults to 2000000000)
        top:'auto', // Top position relative to parent in px
        left:'auto' // Left position relative to parent in px
    };

    var target = document.getElementById('video_spinner');
    $(document).ready(
            function () {
    <g:remoteFunction controller="twitterStream" action="doStart" onComplete="startPooling"/>
    var spinner = new Spinner(opts).spin(target);
    });

</r:script>

