<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Tviwi"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <r:require modules="bootstrap, videojs, spinjs"/>
    <link href="http://vjs.zencdn.net/c/video-js.css" rel="stylesheet">

    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'video-js.css')}" type="text/css">
    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body class="original">
<div id="grailsLogos" class="navbar-inner" role="banner"><a href="${resource(uri: '/')}"><img
        src="${resource(dir: 'images', file: 'twivi.gif')}" alt="Tviwi"/></a></div>
<g:layoutBody/>
<div class="footer navbar-inner" role="contentinfo">In God we trust, cause CODE is evil!</div>

<r:layoutResources/>
</body>
</html>