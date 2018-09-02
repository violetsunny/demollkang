$(".show-detail-content").hide();
$(".show-more").on('click', function() {
    if ($(".show-more-tr").text() == '展开更多') {
        $(".show-detail-content").slideDown(300);
        $(".show-more-tr").text('收起')
    } else {
        $(".show-detail-content").slideUp(300);
        $(".show-more-tr").text('展开更多')
    }
})