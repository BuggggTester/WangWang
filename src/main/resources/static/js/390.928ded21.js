"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[390],{1241:function(e,t){t.A=(e,t)=>{const l=e.__vccOpts||e;for(const[a,n]of t)l[a]=n;return l}},7488:function(e,t,l){function a(e,t,l){const a=new Date;a.setTime(a.getTime()+24*l*60*60*1e3);const n="expires="+a.toUTCString();document.cookie=e+"="+t+";"+n+";path=/"}function n(e){const t=e+"=",l=decodeURIComponent(document.cookie),a=l.split(";");for(let n=0;n<a.length;n++){let e=a[n];while(" "===e.charAt(0))e=e.substring(1);if(0===e.indexOf(t))return e.substring(t.length,e.length)}return""}function o(e){document.cookie=e+"=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/;"}function i(){const e=decodeURIComponent(document.cookie),t=e.split(";");return t.length}t.Ay={setCookie:a,getCookie:n,getCookieNum:i,deleteCookie:o}},7175:function(e,t,l){l.d(t,{A:function(){return g}});var a=l(6768),n="data:image/png;base64,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";const o=e=>((0,a.Qi)("data-v-4acd47db"),e=e(),(0,a.jt)(),e),i={class:"container"},u={class:"top-section"},r={class:"blue-box"},c={style:{display:"flex","flex-direction":"row"}},d=o((()=>(0,a.Lk)("img",{src:n,alt:"暂无"},null,-1))),s={class:"character"},k=o((()=>(0,a.Lk)("br",null,null,-1))),v=o((()=>(0,a.Lk)("div",{class:"spacer"},null,-1))),p=o((()=>(0,a.Lk)("div",{class:"bottom-section"},[(0,a.Lk)("div",{class:"yellow-box"},[(0,a.Lk)("p",{class:"character"},[(0,a.eW)("温馨提示:"),(0,a.Lk)("br"),(0,a.eW)(" 1.席位已锁定，请在指定时间内完成网上支付。 "),(0,a.Lk)("br"),(0,a.eW)(" 2.逾期未支付，系统将取消本次交易。 "),(0,a.Lk)("br"),(0,a.eW)(" 3.在完成支付或取消本订单之前，您将无法购买其他车票。 "),(0,a.Lk)("br"),(0,a.eW)(" 4.未尽事宜详见《国铁集团铁路旅客运输规程》《广深港高速铁路跨境旅客运输组织规则》《中老铁路跨境旅客联运组织规则》等有关规定和车站公告。 ")])])],-1)));function b(e,t,l,n,o,b){const f=(0,a.g2)("el-link");return(0,a.uX)(),(0,a.CE)("div",i,[(0,a.Lk)("div",u,[(0,a.Lk)("div",r,[(0,a.Lk)("div",c,[d,(0,a.Lk)("p",s,[(0,a.eW)("您没有未完成的订单哦～"),k,(0,a.eW)(" 您可以通过"),(0,a.bF)(f,{type:"primary",style:{"font-size":"20px"}},{default:(0,a.k6)((()=>[(0,a.eW)("车票预订")])),_:1}),(0,a.eW)("功能，来制定出行计划。")])])])]),v,p])}var f={name:"NoOrder"},W=l(1241);const x=(0,W.A)(f,[["render",b],["__scopeId","data-v-4acd47db"]]);var g=x},5390:function(e,t,l){l.r(t),l.d(t,{default:function(){return W}});var a=l(6768),n=l(4232),o=l(144),i=l(8073),u=l(7488),r=l(7175);const c={class:"common-layout"},d=(0,a.Lk)("div",{class:"card-header"},[(0,a.Lk)("span",null,[(0,a.Lk)("b",null,"行程信息")])],-1),s={class:"trip-info"},k=(0,a.Lk)("span",null,"您确定要取消该订单吗？",-1),v={class:"dialog-footer"},p={key:1,class:"no-trip-card"};var b={__name:"NotDeparture",setup(e){const t=(0,o.KR)(""),l=(0,o.KR)(""),b=((0,o.KR)(""),(0,o.KR)(""),(0,o.KR)([])),f=[{value:"orderTime",label:"按订单日期查询"},{value:"startTime",label:"按发车时间查询"}];let W=0;function x(e){var t=new Date(e),l=t.getFullYear()+"-",a=(t.getMonth()+1<10?"0"+(t.getMonth()+1):t.getMonth()+1)+"-",n=t.getDate()<10?"0"+t.getDate():t.getDate(),o=(t.getHours()<10?"0"+t.getHours():t.getHours())+":",i=(t.getMinutes()<10?"0"+t.getMinutes():t.getMinutes())+":",u=t.getSeconds()<10?"0"+t.getSeconds():t.getSeconds();return l+a+n+" "+o+i+u}const g=async()=>{console.log(t.value);const e={};e[t.value]=x(l.value);const a=u.Ay.getCookie("userId");console.log(x(l.value));try{const t=await i.Ay.get(`/order/select/notdeparture/${a}/orderTime`,e);b.value=t.data,console.log(b.value.length),0===b.value.length&&(W=1)}catch(n){console.error(n)}},F=(new Date(2e3,1,1,12,0,0),[{text:"今天",value:new Date},{text:"昨天",value:()=>{const e=new Date;return e.setDate(e.getDate()-1),e}},{text:"一周前",value:()=>{const e=new Date;return e.setDate(e.getDate()-7),e}}]);return(e,i)=>{const u=(0,a.g2)("el-option"),x=(0,a.g2)("el-select"),y=(0,a.g2)("el-col"),X=(0,a.g2)("el-date-picker"),h=(0,a.g2)("el-button"),m=(0,a.g2)("el-row"),D=(0,a.g2)("el-header"),V=(0,a.g2)("el-dialog"),C=(0,a.g2)("el-card"),L=(0,a.g2)("el-main"),w=(0,a.g2)("el-container");return(0,a.uX)(),(0,a.CE)("div",c,[(0,a.bF)(w,null,{default:(0,a.k6)((()=>[(0,a.bF)(D,null,{default:(0,a.k6)((()=>[(0,a.bF)(m,{gutter:20},{default:(0,a.k6)((()=>[(0,a.bF)(y,{span:6},{default:(0,a.k6)((()=>[(0,a.bF)(x,{modelValue:t.value,"onUpdate:modelValue":i[0]||(i[0]=e=>t.value=e),size:"large",placeholder:"选择查询方式",style:{width:"240px"}},{default:(0,a.k6)((()=>[((0,a.uX)(),(0,a.CE)(a.FK,null,(0,a.pI)(f,(e=>(0,a.bF)(u,{key:e.value,label:e.label,value:e.value},null,8,["label","value"]))),64))])),_:1},8,["modelValue"])])),_:1}),(0,a.bF)(y,{span:6},{default:(0,a.k6)((()=>[(0,a.bF)(X,{modelValue:l.value,"onUpdate:modelValue":i[1]||(i[1]=e=>l.value=e),type:"datetime",placeholder:"请选择起始日期",shortcuts:F},null,8,["modelValue"])])),_:1}),(0,a.bF)(y,{span:6},{default:(0,a.k6)((()=>[(0,a.bF)(h,{type:"primary",plain:"",onClick:g},{default:(0,a.k6)((()=>[(0,a.eW)("查询")])),_:1})])),_:1})])),_:1})])),_:1}),(0,a.bF)(L,null,{default:(0,a.k6)((()=>[b.value.length>0?((0,a.uX)(!0),(0,a.CE)(a.FK,{key:0},(0,a.pI)(b.value,(t=>((0,a.uX)(),(0,a.CE)("div",{key:t.order_id},[(0,a.bF)(C,{class:"trip-card"},{header:(0,a.k6)((()=>[d])),footer:(0,a.k6)((()=>[(0,a.bF)(h,null,{default:(0,a.k6)((()=>[(0,a.eW)("查看订单")])),_:1}),(0,a.bF)(h,{type:"primary",plain:"",onClick:e.showDetailedOrder},{default:(0,a.k6)((()=>[(0,a.eW)("修改订单")])),_:1},8,["onClick"]),"canceled"!==t.state?((0,a.uX)(),(0,a.Wv)(h,{key:0,type:"danger",plain:"",onClick:e.openCancelDialog},{default:(0,a.k6)((()=>[(0,a.eW)("取消订单")])),_:1},8,["onClick"])):((0,a.uX)(),(0,a.Wv)(h,{key:1,type:"danger",plain:"",disabled:""},{default:(0,a.k6)((()=>[(0,a.eW)("订单已被取消")])),_:1})),e.dialogVisible?((0,a.uX)(),(0,a.Wv)(V,{key:2,visible:e.dialogVisible,title:"取消订单",width:"500"},{footer:(0,a.k6)((()=>[(0,a.Lk)("div",v,[(0,a.bF)(h,{onClick:e.closeCancelDialog},{default:(0,a.k6)((()=>[(0,a.eW)("返回")])),_:1},8,["onClick"]),(0,a.bF)(h,{type:"primary",onClick:l=>e.cancelOrder(t)},{default:(0,a.k6)((()=>[(0,a.eW)(" 残忍取消 ")])),_:2},1032,["onClick"])])])),default:(0,a.k6)((()=>[k])),_:2},1032,["visible"])):(0,a.Q3)("",!0)])),default:(0,a.k6)((()=>[(0,a.Lk)("div",s,[(0,a.Lk)("div",null,[(0,a.Lk)("b",null,"车次："+(0,n.v_)(t.trip.train_id),1)]),(0,a.Lk)("div",null,[(0,a.Lk)("b",null,"起始站："+(0,n.v_)(t.trip.from_place),1)]),(0,a.Lk)("div",null,[(0,a.Lk)("b",null,"终点站："+(0,n.v_)(t.trip.to_place),1)]),(0,a.Lk)("div",null,[(0,a.Lk)("b",null,"发车时间："+(0,n.v_)(t.trip.start_time),1)]),(0,a.Lk)("div",null,[(0,a.Lk)("b",null,"座位："+(0,n.v_)(t.row)+" 排 "+(0,n.v_)(t.seat)+" 座",1)])])])),_:2},1024)])))),128)):(0,a.Q3)("",!0),1===(0,o.R1)(W)?((0,a.uX)(),(0,a.CE)("div",p,[(0,a.bF)(r.A)])):(0,a.Q3)("",!0)])),_:1})])),_:1})])}}};const f=b;var W=f}}]);
//# sourceMappingURL=390.928ded21.js.map