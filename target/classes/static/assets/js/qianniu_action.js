$(function() {
	var uploader;
	$.get("/upload/qnparams", function(data){
		uploader = Qiniu.uploader({
			runtimes : 'html5,flash,html4', // 上传模式,依次退化
			browse_button : 'qnfile', // 上传选择的点选按钮，**必需**
			uptoken_url : '/upload/qntoken',
			domain : data.domain, // bucket 域名，下载资源时用到，**必需**
			max_file_size : data.max_file_size, // 最大文件体积限制
			max_retries : data.max_retries, // 上传失败最大重试次数
			auto_start : false, // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
			unique_names: true,
			//container : 'container', // 上传区域DOM ID，默认是browser_button的父元素，
			// flash_swf_url: 'js/plupload/Moxie.swf', //引入flash,相对路径
			//dragdrop : true, // 开启可拖曳上传
			//drop_element : 'container', // 拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
			//chunk_size : '4mb', // 分块上传时，每片的体积
			
			// 设置一次只能选择一个文件
			multi_selection: false,
			views: {
	            list: true,
	            thumbs: true, // Show thumbs
	            active: 'thumbs'
	        },
			init : {
				'FilesAdded' : function(up, files) {
//					plupload.each(files, function(file) {
						// 文件添加进队列后,处理相关的事情
//					});
				},
				'QueueChanged' : function(up) {
					//alert(up.files);
				},
				'BeforeUpload' : function(up, file) {
					// 每个文件上传前,处理相关的事情
				},
				'UploadProgress' : function(up, file) {
					// 每个文件上传时,处理相关的事情
				},
				'FileUploaded' : function(up, file, info) {
					
				},
				'Error' : function(up, err, errTip) {
					layer.msg(err, {
						offset: ['85px', ''],
								});
					layer.msg(errTip, {
						offset: ['85px', ''],
					});
					// 上传出错时,处理相关的事情
				},
				'UploadComplete' : function() {
					// 队列文件处理完毕后,处理相关的事情
				},
				'Key' : function(up, file) {
					// 若想在前端对每个文件的key进行个性化处理，可以配置该函数
					// 该配置必须要在 unique_names: false , save_key: false 时才生效
					var key = "";
					// do something with key here
					return key
				}
			}
		});
	});
	
	$.removeQueue = function(key){
		uploader.removeFile(key);
	}
	
	$.getUploader = function(){
		return uploader;
	}
	
	
});

function previewImage(file, callback) {	//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
    if (!file || !/image\//.test(file.type)) 
    	return; //确保文件是图片
    if (file.type == 'image/gif') {//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
        var fr = new mOxie.FileReader();
        fr.onload = function () {
            callback(fr.result);
            fr.destroy();
            fr = null;
        }
        fr.readAsDataURL(file.getSource());
    } else {
        var preloader = new mOxie.Image();
        preloader.onload = function () {
            //preloader.downsize(550, 400);//先压缩一下要预览的图片,宽300，高300
            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
            callback && callback(imgsrc); //callback传入的参数为预览图片的url
            preloader.destroy();
            preloader = null;
        };
        preloader.load(file.getSource());
    }
}

function delFile(sourceLink){
	console.log("del file : "+sourceLink);
	var index = sourceLink.lastIndexOf("/");
	var key = sourceLink.substring(index+1, sourceLink.length);
	console.log("del file key: "+key);
	$.get("/upload/delete", {key : key}, function(data){
		console.log("del qiniu file : "+data);
	});
}

function getProjectName(){
	var pathName = window.document.location.pathname;
	var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	return projectName;
}

