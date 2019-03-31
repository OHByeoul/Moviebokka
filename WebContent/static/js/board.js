
            var paging = {
                'data' : {
                	totalRowData : 62, // 전체 데이터 수
                    pageIndexSize : 5, // 인덱스 개수
                    pagingSize : 5, // 한페이지에 보여줄 데이터수
                	currentPage : 1, // 현재 페이지
                    startIndex : 1,
                    endIndex : 5
                },
                'initSetting' : function(opt){
                    if(typeof opt != "object") return;
                    for(val in opt){
                        if(val in this.data){
                        	console.log("inininin???");
                            this.data[val] = opt[val];
                        }
                    }
                    console.log(this.data["currentPage"]);
                    console.log(this.data["totalRowData"]);
                    console.log(this.data["startIndex"]);
                    console.log(this.data["endIndex"]);
                },
                'setHtml' : function(){
                	//if(obj == "object"){
                		console.log("inintsetting");
                    	paging.initSetting(obj);
                	//}
                    console.log("setHtml");
                    var _this = this;
                    var tag = '';
                    var finalIndex = Math.ceil(_this.data.totalRowData/_this.data.pagingSize);
                    
                    if((_this.data.startIndex>_this.data.pageIndexSize)){ 
                        tag += "<a onclick='paging.movePrevPage();' class='prev_page'><span id='prev'>◀ Prev</span></a>";
                    }
        
                   tag += "<span class='numbox'>";
        
                    tag = _this.settingIndexGroup(tag,finalIndex);
        
                    tag += "</span>"
                    
                    if(_this.data.endIndex<=finalIndex){
                        tag += "<a onclick='paging.moveNextPage();' class='next_page'><span id = 'next'> Next ▶</span></a>";
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
                            tag += "<a href='/grade/paging?startNum="+i+"' class='num'>"+" "+i+" " + "</a>";
                        }
                    }
                    return tag;
                },
        
                'movePrevPage' : function(){
                    var _this = this;
                    _this.data.currentPage --;
                    _this.data.startIndex = (_this.data.currentPage-1)*_this.data.pageIndexSize+1;
                    _this.data.endIndex = _this.data.currentPage*_this.data.pageIndexSize;
                    _this.reDrawPage();
                },
        
                'moveNextPage' : function(){
                    var _this = this;
                    // _this.data.currentPage += _this.data.pageIndexSize;
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
        
            window.onload = function(){
                var divTag = document.createElement("div");
                divTag.setAttribute("id","paging");

                divTag.innerHTML = paging.setHtml();
                document.body.appendChild(divTag);    
            }

