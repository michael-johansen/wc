(function ($) {
    var ERROR_RETRY_TIME_MS = 1000;

    function handleSuccess(data) {
        if("timeout" === data){
            subscribe();
        } else {
            $("#message").prop("disabled", false);
            $("table").load(window.location.href + " table", subscribe);
        }
    }

    function handleError() {
        $("#message").prop("disabled", true);
        setTimeout(subscribe, ERROR_RETRY_TIME_MS);
    }

    function subscribe() {
        $.ajax({
            type: "GET",
            url: "/chat/notify",
            success: handleSuccess,
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