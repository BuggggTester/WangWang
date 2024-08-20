"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[840],{7488:function(e,t,a){function o(e,t,a){const o=new Date;o.setTime(o.getTime()+24*a*60*60*1e3);const l="expires="+o.toUTCString();document.cookie=e+"="+t+";"+l+";path=/"}function l(e){const t=e+"=",a=decodeURIComponent(document.cookie),o=a.split(";");for(let l=0;l<o.length;l++){let e=o[l];while(" "===e.charAt(0))e=e.substring(1);if(0===e.indexOf(t))return e.substring(t.length,e.length)}return""}function n(e){document.cookie=e+"=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/;"}function u(){const e=decodeURIComponent(document.cookie),t=e.split(";");return t.length}t.Ay={setCookie:o,getCookie:l,getCookieNum:u,deleteCookie:n}},1407:function(e,t,a){var o=a(8073);async function l(e,t,a,l){await o.Ay.post("message/create",{send:e,receive:t,title:a,body:l})}async function n(e){const t=await o.Ay.get("message/unreadnumberselect",{receive:e});return console.log(t.data),t.data}t.Ay={createMessage:l,getUnreadMessageNumber:n}},4840:function(e,t,a){a.r(t),a.d(t,{default:function(){return C}});a(4114);var o=a(6768),l=a(144),n=a(4232),u=a(8073),i=a(1387),s=a(1219),r=a(9325),c=a(1407),d=a(7488);const p=(0,o.Lk)("p",{style:{"font-size":"25px","font-family":"serif","margin-bottom":"10px"}},"  火车餐购买",-1),g={style:{"font-size":"20px"}},k=["src"],y={style:{color:"#6b778c"}},f={style:{"font-size":"20px","margin-top":"5px",color:"#ff8800"},type:"flex"},m=(0,o.Lk)("span",{style:{"font-size":"larger"}},"请选择支付方式: ",-1),h=(0,o.Lk)("div",{class:"component"},[(0,o.Lk)("hr")],-1),v=(0,o.Lk)("i",{class:"icon-wechat"},null,-1),b=(0,o.Lk)("i",{class:"icon-alipay"},null,-1),_=(0,o.Lk)("i",{class:"icon-bank"},null,-1);var w={__name:"Meal",setup(e){const t=(0,i.lq)();let a=(0,l.KR)([]),w=(0,l.KR)(0),F=(0,l.KR)(0);console.log(t.query.trainId);let C=(0,l.KR)([]);(0,o.sV)((async()=>{const e=await u.Ay.get("food/select/tripId",{trainId:t.query.trainId,time:t.query.time});a.value=e.data,a.value.forEach((e=>{e.quantity=0})),console.log(a.value),w.value=a.value.length,F.value=Math.floor((w.value-1)/3)+1;while(w.value>0)C.value.push(w.value<3?w.value:3),w.value-=3;console.log(F.value),console.log(C.value)}));const R=e=>{},A=(0,l.KR)(""),x=(0,l.KR)(!1),I=async e=>{x.value=!0,await L(e)},q=(0,l.KR)(""),L=async e=>{const a=await u.Ay.post("/food/create/reservation",{user_id:d.Ay.getCookie("userId"),food_id:e.id,quantity:e.quantity,trip_id:t.query.trainId}),o=await u.Ay.post("/totalorder/create",{food_id:e.id,user_id:d.Ay.getCookie("userId"),quantity:e.quantity,order_type:"TRAIN_MEAL",reservation_id:a.data.reservationId,payment:e.price*e.quantity});q.value=o.data.id,console.log(q.value)},V=async()=>{if(""==A.value)return void(0,s.nk)({message:"请选择支付方式！",type:"warning"});const e=await u.Ay.get("/totalorder/confirm",{id:q.value}).then((t=>{console.log(t.data),console.log(e.data)})).catch((e=>{console.error(e)}));s.nk.success("订餐成功"),await M(),x.value=!1},K=async()=>{await u.Ay.post("/order/cancel",{state:"canceled"}).then((e=>{console.log(e.data)})).catch((e=>{console.error(e)}));s.nk.success("取消订餐成功"),await U(),x.value=!1},M=async()=>{await c.Ay.createMessage("汪汪旅途",d.Ay.getCookie("userId"),"火车餐订购成功",`您的${t.query.trainId}车次的火车餐已购买成功，祝您旅途愉快以及用餐愉快，汪汪~`)},U=async()=>{await c.Ay.createMessage("汪汪旅途",d.Ay.getCookie("userId"),"火车餐取消成功",`您的${t.query.trainId}车次的火车餐已取消，您没饭吃了哦，祝您旅途愉快以及各种愉快，汪汪~`)},T=()=>{r.A.push({path:"/main"})};return(e,t)=>{const i=(0,o.g2)("el-page-header"),s=(0,o.g2)("el-col"),r=(0,o.g2)("el-row"),c=(0,o.g2)("el-input-number"),d=(0,o.g2)("el-button"),w=(0,o.g2)("el-card"),q=(0,o.g2)("el-scrollbar"),L=(0,o.g2)("el-radio-button"),M=(0,o.g2)("el-radio-group"),U=(0,o.g2)("el-dialog");return(0,o.uX)(),(0,o.CE)(o.FK,null,[(0,o.bF)(i,{icon:e.ArrowLeft,onBack:T},{content:(0,o.k6)((()=>[p])),_:1},8,["icon"]),(0,o.Lk)("div",null,[(0,o.bF)(q,{height:"100%"},{default:(0,o.k6)((()=>[((0,o.uX)(!0),(0,o.CE)(o.FK,null,(0,o.pI)((0,l.R1)(F),(e=>((0,o.uX)(),(0,o.Wv)(r,{gutter:20,justify:"space-around"},{default:(0,o.k6)((()=>[((0,o.uX)(!0),(0,o.CE)(o.FK,null,(0,o.pI)((0,l.R1)(C)[e-1],(t=>((0,o.uX)(),(0,o.Wv)(s,{key:t,span:7},{default:(0,o.k6)((()=>[(0,o.bF)(w,{class:"card",shadow:"always","body-style":{height:"125px",width:"100%",boxSizing:"border-box"}},{header:(0,o.k6)((()=>[(0,o.Lk)("p",g,(0,n.v_)((0,l.R1)(a)[t-1+3*(e-1)].name),1)])),footer:(0,o.k6)((()=>[(0,o.bF)(r,{gutter:15},{default:(0,o.k6)((()=>[(0,o.bF)(s,{span:6},{default:(0,o.k6)((()=>[(0,o.Lk)("p",f,"￥"+(0,n.v_)((0,l.R1)(a)[t-1+3*(e-1)].price),1)])),_:2},1024),(0,o.bF)(s,{span:8},{default:(0,o.k6)((()=>[(0,o.bF)(c,{modelValue:(0,l.R1)(a)[t-1+3*(e-1)].quantity,"onUpdate:modelValue":o=>(0,l.R1)(a)[t-1+3*(e-1)].quantity=o,min:1,max:10,onChange:o=>R((0,l.R1)(a)[t-1+3*(e-1)])},null,8,["modelValue","onUpdate:modelValue","onChange"])])),_:2},1024),(0,o.bF)(s,{span:10},{default:(0,o.k6)((()=>[(0,o.bF)(d,{type:"primary",plain:"",class:"button",onClick:o=>I((0,l.R1)(a)[t-1+3*(e-1)])},{default:(0,o.k6)((()=>[(0,o.eW)("立即购买")])),_:2},1032,["onClick"])])),_:2},1024)])),_:2},1024)])),default:(0,o.k6)((()=>[(0,o.bF)(r,{gutter:50},{default:(0,o.k6)((()=>[(0,o.bF)(s,{span:10},{default:(0,o.k6)((()=>[(0,o.Lk)("img",{src:(0,l.R1)(u.Tt)()+(0,l.R1)(a)[t-1+3*(e-1)].picture_path,width:"170"},null,8,k)])),_:2},1024),(0,o.bF)(s,{span:13},{default:(0,o.k6)((()=>[(0,o.Lk)("p",y,(0,n.v_)((0,l.R1)(a)[t-1+3*(e-1)].description),1)])),_:2},1024)])),_:2},1024)])),_:2},1024)])),_:2},1024)))),128))])),_:2},1024)))),256))])),_:1})]),(0,o.bF)(U,{modelValue:x.value,"onUpdate:modelValue":t[1]||(t[1]=e=>x.value=e)},{default:(0,o.k6)((()=>[(0,o.bF)(w,null,{default:(0,o.k6)((()=>[m,h,(0,o.bF)(M,{modelValue:A.value,"onUpdate:modelValue":t[0]||(t[0]=e=>A.value=e)},{default:(0,o.k6)((()=>[(0,o.bF)(L,{label:"wechat"},{default:(0,o.k6)((()=>[v])),_:1}),(0,o.bF)(L,{label:"alipay"},{default:(0,o.k6)((()=>[b])),_:1}),(0,o.bF)(L,{label:"bank"},{default:(0,o.k6)((()=>[_])),_:1})])),_:1},8,["modelValue"]),(0,o.bF)(d,{onClick:V,type:"primary",style:{"margin-left":"15%","margin-top":"10px",height:"40px",width:"200px"}},{default:(0,o.k6)((()=>[(0,o.eW)("确认支付 ")])),_:1}),(0,o.bF)(d,{onClick:K,style:{"margin-left":"20%","margin-top":"10px",height:"40px",width:"200px"}},{default:(0,o.k6)((()=>[(0,o.eW)("取消订单 ")])),_:1})])),_:1})])),_:1},8,["modelValue"])],64)}}};const F=w;var C=F}}]);
//# sourceMappingURL=840.63b0dd77.js.map