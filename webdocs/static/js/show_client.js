/**
 * Created by kllp0648 on 2017/12/6.
 */
$('.originShowBtn').click(function () {
    var btn$ = $(this);
    var show$ = $('.originShow');
    if (btn$.text() === '查看更多') {
        show$.slideDown(300);
        btn$.text('收起');
    } else {
        show$.slideUp(300);
        btn$.text('查看更多');
    }
});