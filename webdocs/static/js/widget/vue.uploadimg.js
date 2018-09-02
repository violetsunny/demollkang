/**
 * Created by kllp0648 on 2017/12/8.
 */
Vue.component('vue-uploadimg', {
    template:
    '<ul class="list list-float">'+
        '<input type="file" v-on:change="onFileChange" multiple style="display: none;">'+
        '<li v-for="(key,image) in images" track-by="$index">'+
            '<div>'+
                '<a href="{{image}}" target="_blank"><img class="upLoadImg" :src="image"/></a>'+
                '<a href="javascript:;" v-on:click="delImage(key)">'+
                    '<span class="glyphicon glyphicon-remove" style="float: right"></span>'+
                '</a>'+
            '</div>'+
        '</li>'+
    '</ul>'+
    '<div style="margin: 20px 44px 0 0;">'+
        '<button  v-show="(images.length)<9"   type="button" class="btn-xm-blue btn-radius" style="margin-right: 50px" v-on:click="addPic"  id="addPic">选择证明文件</button>'+
        '<button  type="button" class="btn-xm-blue btn-radius" style="margin-right: 200px" v-on:click="uploadImage">上传</button>'+
    '</div>',
    props: {
        images: {
            type: Array,
            required: false,
            default: function() {
                return [];
            }
        },
        imgnames: {
            type: Array,
            required: false,
            default: []
        },
        filevalue: {
            type: Array,
            required: false,
            default: function() {
                return [];
            }
        },
        fileurls: {
            type: Array,
            required: true
        }
    },
    computed: {

    },
    methods: {
        addPic:function(e){
            e.preventDefault();
            $('input[type=file]').trigger('click');
            return false;
        },
        onFileChange:function(e) {
            var files = e.target.files || e.dataTransfer.files;
            if(files.length>=10){
                toastr.error("最多只能上传9张图片!","", {
                    timeOut: 2000,
                    positionClass: "toast-top-center"
                });
                return;
            }
            var flag = true;
            _.forEach(files, function (file) {
                var fileName=file.name;
                var extStart = fileName.lastIndexOf(".") + 1;
                var ext = fileName.substring(extStart, fileName.length).toLowerCase();
                if (ext != "bmp" && ext != "png" && ext != "gif" && ext != "jpeg" && ext != "jpg" ){
                    flag = false;
                    return false;
                }
            });
            if(!flag){
                toastr.error("图片类型必须是.gif,jpeg,jpg,png,bmp中的一种!","", {
                    timeOut: 2000,
                    positionClass: "toast-top-center"
                });
                $('input[type=file]').val('');
                return;
            }

            var imageslen = this.images.length;
            var originLength = this.fileurls.length;
            var newFileLen = files.length;
            var totalFileLen = newFileLen + imageslen + originLength;
            if(totalFileLen>=10){
                toastr.error("最多只能上传9张图片!","", {
                    timeOut: 2000,
                    positionClass: "toast-top-center"
                });
                return;
            }

            if (!files.length)return;

            var filevalues = [];
            $(files).each(function (i, e) {
                filevalues.push(e);
            });
            this.filevalue = filevalues;
            this.createImage(files);
        },
        createImage:function(file) {
            if(typeof FileReader==='undefined'){
                alert('您的浏览器不支持图片上传，请升级您的浏览器');
                return false;
            }
            for (var i = 0; i < file.length; i++) {
                var filesize = file[i].size;
                if (filesize > 10  * 1024 * 1024) {
                    toastr.error("仅允许10M以内文件,文件："+file[i].name+"，大小超过限制", {
                        timeOut: 2000,
                        positionClass: "toast-top-center"
                    });
                    return;
                }
            }

            var vm = this;
            var image = new Image();
            var leng=file.length;
            for(var i=0;i<leng;i++){
                var name = file[i].name;
                vm.imgnames.push(name);

                var reader = new FileReader();
                reader.readAsDataURL(file[i]);
                reader.onload =function(e){
                    vm.images.push(e.target.result);
                };
            }
            $('input[type=file]').val('');
        },
        delImage:function(index){
            this.images.splice(index,1);
            this.imgnames.splice(index,1);
            this.filevalue.splice(index, 1);
        },
        uploadImage : function(){
            if(this.filevalue.length==0){
                toastr.error("请先选择图片!","", {
                    timeOut: 2000,
                    positionClass: "toast-top-center"
                });

                return;
            }
            var vm = this;
            var formData = new FormData();
            for (var i=0;i<vm.filevalue.length;i++) {
                formData.append('files', vm.filevalue[i]);
            }

            $.ajax({
                type: "POST",
                url: __ctx + "/img/upload",
                contentType: false,    // 这个一定要写
                processData: false,    // 这个也一定要写，不然会报错
                data: formData,
                dataType: "json",
                success: function(result) {
                    if(result.result){
                        toastr.success("图片上传成功！", "",{
                            timeOut: 2000,
                            positionClass: "toast-top-center"
                        });
                        vm.fileurls=[];
                        var leng = result.obj.length;
                        if (leng > 0) {
                            for (var i = 0; i < leng; i++) {
                                var url = result.obj[i].fileUrl;
                                vm.fileurls.push(url);
                                vm.images.$set(i,url);
                            }
                        }
                    }
                }
            });

        },
        enlargeImage:function(index){
            window.open(index);
        },
        validImagsIsUpload:function() {
            var p = _.findIndex(this.images, function (url) {
                return url.indexOf('data:') >= 0;
            });
            return p >= 0;
        }
    }



});