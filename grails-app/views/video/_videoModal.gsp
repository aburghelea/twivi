%{--<img src="${resource(dir: 'images', file: 'dancing-mouse.gif')}" alt="Soarecu danseaza pentru"/>--}%
<g:if test="${token != null}">
    <video id="twivi_video" class="video-js vjs-default-skin" controls
           width="520" height="380"
           poster="${resource(dir: 'images', file: 'dancing-mouse.gif')}"
           preload="auto">
        <source type="video/mp4"
                src="${createLink(controller: 'videoProducer', action: 'stream', params: [query: token])}"/>
    </video>

    <script>
        var myPlayer = _V_("twivi_video");
    </script>
</g:if>
<g:else>
    No video for you, have a mouse;
    <g:img file="dancing-mouse.gif"/>
</g:else>