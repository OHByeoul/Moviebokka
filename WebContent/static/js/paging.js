
            var paging = {
                'data' : {
                	totalRowData : 0, // 전체 데이터 수
                    pageIndexSize : 0, // 인덱스 개수
                    pagingSize : 0, // 한페이지에 보여줄 데이터수
                	currentPage : 0, // 현재 페이지
                    startIndex : 0,
                    endIndex : 0
                },
                'initSetting' : function(opt){
                    if(typeof opt != "object") return;
                    for(val in opt){
                        if(val in this.data){
                            this.data[val] = opt[val];
                        }
                    }
                },
                'setHtml' : function(){
                   	paging.initSetting(obj);

                    console.log("setHtml");
                    var _this = this;
                    var tag = '';
                    var finalIndex = Math.ceil(_this.data.totalRowData/_this.data.pagingSize);
                    
                    if((_this.data.startIndex>_this.data.pageIndexSize)){ 
                        tag += "<a onclick='paging.movePrevPage();' class='prev_page'><span id='prev'>< Prev</span></a>";
                    }
        
                   tag += "<span class='numbox'>";
        
                    tag = _this.settingIndexGroup(tag,finalIndex);
        
                    tag += "</span>"
                    
                    if(_this.data.endIndex<=finalIndex){
                        tag += "<a onclick='paging.moveNextPage();' class='next_page'><span id = 'next'> Next ></span></a>";
                    }

                    return tag;
                },
        
                'settingIndexGroup' : function(tag,finalIndex){
                    console.log("settingIndexGroup");
                    var _this = this;
                  
                   var startIndex = _this.data.startIndex;
                   var endIndex = _this.data.endIndex;

                    for(let i = startIndex; i<=endIndex; i++){
                        
                        if(i<=finalIndex){
                            tag += "<a href='/Moviebokka/board/pagingBoard?startNum="+i+"' class='num'>"+" "+i+" " + "</a>";
                        }
                    }
                    return tag;
                },
        
                'movePrevPage' : function(){
                    var _this = this;
                    console.log('click movePrev');
                    _this.data.currentPage --;
                    _this.data.startIndex = (_this.data.currentPage-1)*_this.data.pageIndexSize+1;
                    _this.data.endIndex = _this.data.currentPage*_this.data.pageIndexSize;
                    _this.reDrawPage();
                },
        
                'moveNextPage' : function(){
                    var _this = this;
                    console.log('click moveNext');
                    _this.data.currentPage ++;
                    _this.data.startIndex = (_this.data.currentPage-1)*_this.data.pageIndexSize+1;
                    _this.data.endIndex = _this.data.currentPage*_this.data.pageIndexSize;
                    _this.reDrawPage();
                },
                'reDrawPage' : function(){
                    var getId = document.getElementById("paging");
                    getId.removeChild(getId.childNodes[0]);
                    getId.innerHTML = this.setHtml();
                    document.body.appendChild(getId);                   
                }
            }
                   
            $(function(){
            	var $div = $('<div></div>');
            	$div.attr("id","paging");
            	
            	$div.html(paging.setHtml());
            	var $body = $('body');
            	$body.append($div);
            	
            	$('.prev_page').on('click', $('#prev'), function(){
            		console.log("prev");
            		 location.href = "/Moviebokka/board/pagingPrev?startIndex="+paging.data.startIndex;
            		 $body.empty();
            		 paging.setHtml();
            	});
            	
            	$('.next_page').on('click', $('#next'), function(){
            		console.log("next");
            		 location.href = "/Moviebokka/board/pagingNext?startIndex="+paging.data.startIndex;
            		 $body.empty();
            		 paging.setHtml();
            	});
            });

