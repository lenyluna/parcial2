<#import "common/menu.ftl" as m>
<@m.menu />

<style>
    * {
        box-sizing: border-box;
    }

    html {
        overflow-y: scroll;
    }

    body {
        font-family: monospace;
        font-size: 14px;
        max-width: 480px;
        margin: 0 auto;
        padding: 20px
    }

    input {
        width: 100%;
        padding: 5px;
        margin: 5px 0;
    }

    button {
        float: right;
    }

    li {
        margin: 5px 0;
    }

    #chatControls {
        overflow: auto;
        margin: 0 0 5px 0
    }

    #userlist {
        position: fixed;
        left: 50%;
        list-style: none;
        margin-left: 250px;
        background: #f0f0f9;
        padding: 5px 10px;
        width: 150px;
        top: 11px;
    }

    #chat p {
        margin: 5px 0;
        font-weight: 300
    }

    #chat .timestamp {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 12px;
    }

    #chat article {
        background: #f1f1f1;
        padding: 10px;
        margin: 10px 0;
        border-left: 5px solid #aaa;
        position: relative;
        word-wrap: break-word;
    }

    #chat article:first-of-type {
        background: #c9edc3;
        border-left-color: #74a377;
        animation: enter .2s 1;
    }

    @keyframes enter {
        from { transform: none;        }
        50%  { transform: scale(1.05); }
        to   { transform: none;        }
    }
</style>

<div class="container">

    <div id="chatControls">
        <input id="message" placeholder="Type your message">
        <button id="send">Send</button>
    </div>
    <ul id="userlist"> <!-- Built by JS --> </ul>
    <div id="chat">    <!-- Built by JS --> </div>
    <script src="websocketDemo.js"></script>

</div>

