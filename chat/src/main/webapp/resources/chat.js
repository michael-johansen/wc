(function ($) {
    var ERROR_RETRY_TIME_MS = 1000;
    var ERROR_RETRY_TIMES = 3;
    var retryTimes = 0;

    function reloadMessages() {
        $("#message").prop("disabled", false);
        $("table").load(window.location.href + " table", subscribe);
        retryTimes = 0;
    }

    function handleError() {
        if (retryTimes >= ERROR_RETRY_TIMES) {
            $("#message").prop("disabled", true);
            setTimeout(subscribe, ERROR_RETRY_TIME_MS);
        } else {
            subscribe();
        }
        retryTimes++;
    }

    function subscribe() {
        $.ajax({
            type: "GET",
            url: "/chat/notify",
            success: reloadMessages,
            error: handleError
        });
    }

    function changeName() {
        $.ajax({
            type: "POST",
            url: "/chat/user",
            data: $("#username").serialize()
        });
    }

    subscribe();
    window.chat = {
        changeName: changeName
    }
})(jQuery);