<!DOCTYPE html>
<html style="width:100%;height:100%;padding:0px;margin:0px;">
 <head> 
  <meta charset="utf-8" /> 
 </head> 
 <body style="width: 100%;height: 100%;min-width: 900px;padding: 0px;margin:0px;font-family:Arial,Verdana,Sans-serif;"> 
  <div class="container" style="width:100%;"> 
   <div class="tmc-logo" style="overflow:hidden;"> 
    <div style="float:left;width:100px;height:35px;text-align:left;"> 
     <img src="${oppsiteLogo}" style="height:35px;" /> 
    </div> 
    <div class="logo-line" style="float:left;margin-top: 0px;width:1px;height: 18px;background-color:#e4e4e4;"></div> 
    <div style="float:left;width:133px;height:35px;text-align:left;"> 
     <img src="${tmcLogo}" style="height:35px;" /> 
    </div> 
    <div style="float:right;text-align: right;margin-top: 10px;"> 
     <span style="color: #7591a7;font-size:14px;">服务热线：${telephone}</span>
    </div> 
   </div> 
   <div class="cont-title" style="padding:0px 0px;border-top: 2px solid #7591a7;text-align:center;font-size:20px;font-weight:bold;color:#333;">
    行程单
   </div> 
   <div style="padding:0px;background-color:#f9f9f9;font-size:10px;"> 
    <p style="margin:5px 0px;padding-left:5px;border-left: 2px solid #7591a7;"><span style="display:inline-block;width:80px;font-weight:bold;">差旅人</span><span>&#xa0;${passengerName}&#xa0;${passengerDepartmentName}</span></p>
    <#if showTravelPurpose=="true"> 
    <#if travelAim!="">
    <p style="margin:5px 0px;padding-left:5px;border-left: 2px solid #7591a7;"><span style="display:inline-block;width:80px;font-weight:bold;">差旅目的</span><span>&#xa0;${travelAim}</span></p> </#if></#if>
   </div> 
   <#if  showFlight == "1">
   <#list flights as flight>
   <div class="product-cont" style="margin-top:0px;padding-bottom: 18px;font-size:10px;background-color:#f9f9f9;"> 
    <p style="position:relative;padding: 6px 0px 6px 48px;margin:0px;background-size: auto 14px;background-color:#e5f1f8;color:#666;"><img src="./card_plane.png" style="position:absolute;top:0px;left:0px;height: 20px;">国内机票</p> 
    <div class="flight-content" style="margin: 10px 0px 0px 5px;"> 
    <#list flight.segmentDataVOs as segment>
     <div style="padding: 10px 0px;border-top: 2px solid #7591a7;color:#333;border-bottom: 1px solid #dedae0;">
      ${segment.depDate}&#xa0;${segment.depCity}—&gt;${segment.arrCity}&#xa0;<img src="./airlineCodes/icon_${segment.airlineCompanyCode}.png" style="height:10px;" />&#xa0;${segment.airlineCompany}&#xa0;${segment.flightNo}
     </div> 
     <div style="padding: 10px 0px 10px 0px;border-top: 1px solid #dedae0;border-bottom: 1px solid #dedae0;overflow: hidden;"> 
      <div style="float: left;padding-top: 11px;text-align: center;"> 
       <span style="color:#333;font-weight:bold;margin-bottom:5px;font-size:14px;">${segment.depTime}</span> 
       <br/> 
       <span style="color:#666;">${segment.startPort}(${segment.startPortCode})&#xa0;${segment.startTerminal}</span> 
      </div> 
      <div style="float: left;width: 90px;padding-bottom: 3px;border-bottom:1px solid #d1daec;color:#666;text-align: center;">
       ${segment.flyTimeDesc}
      </div> 
      <div style="float: left;padding-top: 11px;text-align: center;"> 
       <span style="color:#333;font-weight:bold;margin-bottom:5px;font-size:14px;">${segment.arrTime}</span>&#xa0; <span style="color:#333;margin-bottom:5px;font-size:10px;">${segment.overDays}</span>
       <br /> 
       <span style="color:#666;">${segment.endPort}(${segment.endPortCode})&#xa0;${segment.endTerminal}</span> 
      </div> 
      <div style="float: left;margin-left: 82px;">
       ${segment.seatClass}${segment.flightDiscount}折&#xa0;机型:${segment.planModel}&#xa0;餐食:${segment.meals}
      </div> 
     </div> 
     
     <#if  segment.stopSite != "" || segment.shareComment !=""><p style="margin: 5px 0px 0px;color:#999;">${segment.shareComment}<#if  segment.stopSite != "">&#xa0;&#xa0;<span style="margin-left:40px;color:#666;">经停${segment.stopSite}</span></#if></p> </#if>
     <#if  segment.bags != ""><p style="margin: 5px 0px 9px;color:#666;">行李&#xa0;<span style="color:#333;">${segment.bags}</span></p> </#if>
     </#list>
     <div class="flight-info"> 
      <div style="padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
       客票信息
      </div> 
      <div style="margin-left:8px;"> 
       <p style="margin:10px 0px 0px;"> <span style="display: inline-block;width: 30px;color:#666;">票号：</span><span style="color:#333;">${flight.ticketNo}</span>&#xa0; <span style="margin-left:20px;color:#333;">客票有效期：${flight.effecticeTime}年</span><#if  showTicketCode == "true">&#xa0;<#if  flight.bigCode != ""> <span style="margin-left:30px;color:#333;">航空公司预订编号：${flight.airlineCompanyCode}/${flight.bigCode}</span></#if></#if> </p>     
       <#if  showTicketRefundChange == "true"> 
       <#if  flight.refundShowRule == 0> 
       <p style="display:table;margin:10px 0px 0px;"><span style="display:table-cell;width: 33px;color:#666;">退票&#xa0;</span><span style="display:table-cell;color:#333;">${flight.refundText}</span></p> 
       <p style="display:table;margin:10px 0px 0px;"><span style="display:table-cell;width: 33px;color:#666;">改签&#xa0;</span><span style="display:table-cell;color:#333;">${flight.changeText}</span></p> 
       <p style="display:table;margin:10px 0px 0px;"><span style="display:table-cell;width: 33px;color:#666;">签转&#xa0;</span><span style="display:table-cell;color:#333;">${flight.signText}</span></p>
       </#if>
       <#if  flight.refundShowRule == 1>       
       <table style="width:100%;text-align: left;border-bottom: 1px solid #d1dae0;">
           <tr style="height: 30px;border-bottom: 1px solid #d1dae0;">
               <th style="text-align: left;border-bottom: 1px solid #d1dae0;height: 30px;">退改签时间点</th>
               <th style="text-align: left;border-bottom: 1px solid #d1dae0;height: 30px;">退票费/比例</th>
               <th style="text-align: left;border-bottom: 1px solid #d1dae0;height: 30px;">同舱改期费/比例</th>
               <th style="text-align: left;border-bottom: 1px solid #d1dae0;height: 30px;">签转</th>
            </tr>
            <#list flight.refundChangeRules as refundChangeRule>
            <tr style="height: 30px;border-bottom: 1px solid #d1dae0;">
                <td style="text-align: left;border-bottom: 1px solid #d1dae0;height: 30px;">${refundChangeRule.timeInfo}</td>
                <td style="text-align: left;border-bottom: 1px solid #d1dae0;height: 30px;">${refundChangeRule.refundInfo}</td>
                <td style="text-align: left;border-bottom: 1px solid #d1dae0;height: 30px;">${refundChangeRule.changeInfo}</td>
                <td style="text-align: left;border-bottom: 1px solid #d1dae0;height: 30px;">${refundChangeRule.signInfo}</td>
            </tr>
            </#list>
       </table>
       </#if>
       </#if>
      </div> 
     </div> 
     <div style="margin-top: 10px;"> 
      <div style="padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
       乘机证件
      </div> 
      <div style="margin-left:8px;"> 
       <p style="margin:10px 0px 0px;"><span style="color:#666;">${flight.certificateType}：</span><span style="color:#333;">${flight.certificateCode}</span></p> 
      </div> 
     </div>
     <#if flight.violationContent!="">
     <div style="margin-top: 10px;"> 
      <#if showViolationTravelPolicy=="true" && showViolationReason=="true">
      <div style="padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
       违规信息
      </div> 
      <div style="margin-left:8px;">
       <#if showViolationTravelPolicy=="true">
       <p style="margin:10px 0px 0px;"><span style="color:#666;">违反差旅政策：</span><span style="color:#333;">${flight.violationContent}</span></p>
       </#if>
       <#if showViolationReason=="true">
       <p style="margin:10px 0px 0px;"><span style="color:#666;">违规原因：</span><span style="color:#333;">${flight.violationReason}</span></p>
       </#if> 
      </div>
      </#if> 
     </div>
     </#if> 
    </div> 
   </div>
   </#list>
   </#if> 
   <#if  showHotel == "1">
   <#list hotels as hotel>
   <div class="product-cont" style="margin-top:10px;padding-bottom: 18px;font-size:10px;background-color:#f9f9f9;"> 
    <p style="position:relative;padding: 6px 0px 6px 48px;margin:0px;background-size: auto 100%;background-color:#f8e8e9;color:#666;"><img src="./card_hotel.png" style="position:absolute;top:0px;left:0px;height: 20px;">酒店</p> 
    <div class="flight-content" style="margin: 10px 20px 0px 5px;"> 
     <div style="padding: 10px 0px;border-top: 2px solid #7591a7;color:#333;">
      ${hotel.hotelName}
      <span style="margin-left:15px;">${hotel.cityName}</span>
      <#if  hotel.hotelConfirmNo != ""><span style="margin-left:30px;"><span style="margin-right:15px;color:#666;">酒店确认号</span>${hotel.hotelConfirmNo}</span></#if>
     </div> 
     <div style="padding: 16px 0px 10px 0px;border-top: 1px solid #dedae0;border-bottom: 1px solid #dedae0;overflow: hidden;"> 
      <div style="float: left;height: 40px;line-height:20px;text-align: center;color:#333;"> 
       <span>入住&#xa0;${hotel.gmtOccupancyTime}</span> 
       <br /> 
       <span>离店&#xa0;${hotel.gmtLeaveTime}</span> 
      </div> 
      <div style="float: left;margin-left: 45px;height: 40px;line-height: 40px;color:#666;">
       共
       <span style="color:#333;margin-right:10px;">${hotel.days}天</span>房间数
       <span style="color:#333;margin-left:10px;">${hotel.roomNumber}</span>
      </div> 
      <div style="float: left;margin-left: 45px;height: 40px;line-height: 40px;">
       ${hotel.roomName}&#xa0;${hotel.bedTypeName}&#xa0;${hotel.haveBreakfast}
      </div> 
     </div> 
     <div class="flight-info" style="margin-top: 10px;"> 
      <div style="padding-left:5px;border-left:3px solid #7591a7;color:#333;font-weight:bold;">
       酒店信息
      </div> 
      <div style="margin-left:8px;"> 
       <p style="margin:10px 0px 0px;"><span style="color:#666;">电话：</span><span style="color:#333;">${hotel.hotelPhoneNumber}</span></p> 
       <p style="margin:10px 0px 0px;"><span style="color:#666;">地址：</span><span style="color:#333;">${hotel.hotelAddress}</span></p> 
       <p style="margin:10px 0px 0px;"><span style="color:#666;">房间最晚保留时间：</span><span style="color:#333;">${hotel.gmtRetainEndTime}</span><span style="margin-left: 55px;">${hotel.hotelType}<span style="margin-left:5px;font-weight:bold;">${hotel.guaranteeComment}</span></span></p> 
       <p style="margin:10px 0px 0px;"><span style="color:#666;">取消规则：</span><span style="color:#333;font-weight:bold;">${hotel.cancelRule}</span></p> 
      </div> 
     </div> 
     <#if hotel.violationContent!="">
     <div style="margin-top: 10px;"> 
      <#if showViolationTravelPolicy=="true" && showViolationReason=="true">
      <div style="padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
       违规信息
      </div> 
      <div style="margin-left:8px;">
       <#if showViolationTravelPolicy=="true">
       <p style="margin:10px 0px 0px;"><span style="color:#666;">违反差旅政策：</span><span style="color:#333;">${hotel.violationContent}</span></p>
       </#if>
       <#if showViolationReason=="true">
       <p style="margin:10px 0px 0px;"><span style="color:#666;">违规原因：</span><span style="color:#333;">${hotel.violationReason}</span></p>
       </#if>
      </div>
      </#if> 
     </div>
     </#if> 
    </div> 
   </div>
   </#list>
   </#if>
   <#if  showTrain == "1">
   <#list trains as train>
   <div class="product-cont" style="margin-top:10px;padding-bottom: 18px;font-size:10px;background-color:#f9f9f9;"> 
    <p style="position:relative;margin:0px;padding: 6px 0px 6px 48px;background-size: auto 100%;background-color:#e5f4f4;color:#666;"><img src="./card_train.png" style="position:absolute;top:0px;left:0px;height: 20px;">火车票</p> 
    <div class="flight-content" style="margin: 10px 20px 0px 5px;"> 
     <div style="padding: 10px 0px;border-top: 2px solid #7591a7;color:#333;"> 
      <span style="font-weight:bold;margin-right:10px;">${train.planBeginDate}&#xa0;${train.trainNo}</span> 
      <span>${train.seatClass}&#xa0;${train.seatNo}</span> 
      <span style="margin-left:45px;color:#666;">取票号&#xa0;${train.ticketNo}</span> 
     </div> 
     <div style="padding: 16px 0px 10px 0px;border-top: 1px solid #dedae0;border-bottom: 1px solid #dedae0;overflow: hidden;"> 
      <div style="float: left;padding-top: 11px;text-align: center;"> 
       <span style="color:#333;font-weight:bold;margin-bottom:5px;font-size:14px;">${train.planBeginTime}</span> 
       <br /> 
       <span style="color:#666;">${train.fromStation}(${train.depCity})</span> 
      </div> 
      <div style="float: left;width: 110px;padding-bottom: 3px;border-bottom:1px solid #d1daec;color:#666;text-align: center;">
       ${train.minutesDesc}
      </div> 
      <div style="float: left;padding-top: 11px;text-align: center;"> 
       <span style="color:#333;font-weight:bold;margin-bottom:5px;font-size:14px;">${train.planEndTime}</span> &#xa0; <span style="color:#333;margin-bottom:5px;font-size:10px;">${train.overDays}</span>
       <br /> 
       <span style="color:#666;">${train.toStation}(${train.arrCity})</span> 
      </div> 
     </div>
     <#if train.violationContent!=""> 
     <div style="margin-top: 10px;"> 
      <#if showViolationTravelPolicy=="true" && showViolationReason=="true">
      <div style="padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
       违规信息
      </div> 
      <div style="margin-left:8px;">
       <#if showViolationTravelPolicy=="true"> 
       <p style="margin:10px 0px 0px;"><span style="color:#666;">违反差旅政策：</span><span style="color:#333;">${train.violationContent}</span></p>
       </#if>
       <#if showViolationReason=="true">
       <p style="margin:10px 0px 0px;"><span style="color:#666;">违规原因：</span><span style="color:#333;">${train.violationReasonChinese}</span></p>
       </#if> 
      </div>
      </#if> 
     </div>
     </#if> 
    </div> 
   </div>
   </#list>
   </#if>
   <#if  showIntFlight == "1">
   <#list intFlights as inFlight>
   <div class="product-cont" style="margin-top:10px;padding-bottom: 18px;font-size:10px;background-color:#f9f9f9;"> 
    <p style="position:relative;margin:0px;padding: 6px 0px 6px 48px;background-size: auto 100%;background-color:#e5f1f8;color:#666;"><img src="./card_gjplane.png" style="position:absolute;top:0px;left:0px;height: 20px;">国际机票</p> 
    <div class="flight-content" style="margin: 10px 20px 0px 5px;"> 
     <p style="margin:10px 0px;font-size:12px;color:#333;font-weight:bold;">${inFlight.startTime}<span style="margin-left:30px;">${inFlight.startCity}&#xa0;—&gt;&#xa0;${inFlight.endCity}（${inFlight.isOverCheng}）</span></p> 
     <#list inFlight.chengDataVOs as cheng>
     <div>
      <#if  cheng.chengIndexStr != "">
      <div style="margin-bottom: 5px;width:60px;padding: 3px 0px;text-align: center;color: #fff;background-color: #33afef;border-radius: 2px;">
       ${cheng.chengIndexStr}
      </div>
      </#if>
      <#list cheng.segmentDataVOs as segment>
      <div> 
       <div style="padding: 10px 0px;border-top: 2px solid #7591a7;color:#333;">
        ${segment.depDate}&#xa0;${segment.depCity}—&gt;${segment.arrCity}&#xa0;<img src="http://wx.40017.cn/touch/weixin/iflight/img/public/aircompanyLogo/${segment.airlineCode}.png"/>&#xa0;${segment.airline}&#xa0;${segment.flightNo}
       </div> 
       <div style="padding: 16px 0px 10px 0px;border-top: 1px solid #dedae0;border-bottom: 1px solid #dedae0;overflow: hidden;"> 
        <div style="float: left;padding-top: 11px;text-align: center;"> 
         <span style="color:#333;font-weight:bold;margin-bottom:5px;font-size:14px;">${segment.depTime}</span> 
         <br /> 
         <span style="color:#666;">${segment.depPort}(${segment.depPortCode})&#xa0;${segment.depPortTerminal}</span> 
        </div> 
        <div style="float: left;width: 110px;padding-bottom: 3px;border-bottom:1px solid #d1daec;color:#666;text-align: center;">
         ${segment.flyMinuteDesc}
        </div> 
        <div style="float: left;padding-top: 11px;text-align: center;"> 
         <span style="color:#333;font-weight:bold;margin-bottom:5px;font-size:14px;">${segment.arrTime}</span> &#xa0; <span style="color:#333;margin-bottom:5px;font-size:10px;">${segment.overDays}</span>
         <br /> 
         <span style="color:#666;">${segment.arrPort}(${segment.arrPortCode})&#xa0;${segment.arrPortTerminal}</span> 
        </div> 
        <div style="float: left;margin-left: 82px;">
         ${segment.cabinClass}&#xa0;机型:${segment.planModel}&#xa0;
        </div> 
       </div> 
       <p style="margin: 10px 0px 0px;color:#999;">${segment.shareComment}<span style="margin-left:40px;color:#666;">${segment.stopSiteComment}</span></p> 
       <p style="margin: 10px 0px 0px;color:#666;">行李&#xa0;<span style="color:#333;">${segment.bags}</span></p> 
       <p style="margin:10px 0px; padding: 8px;background-color:#f3f3f3;color:#333;font-weight:bold;">${segment.transfer}&#xa0;${segment.transferCity}&#xa0;${segment.transferPort}&#xa0;${segment.transferMinutesDesc}</p> 
      </div>
      </#list> 
     </div>
     </#list>   
     <div class="flight-info"> 
      <div style="padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
       客票信息
      </div> 
      <div style="margin-left:8px;"> 
       <p style="margin:10px 0px 0px;"> <span style="display: inline-block;width: 33px;color:#666;">票号：</span><span style="color:#333;">${inFlight.ticketNos}</span>&#xa0; <span style="margin-left:20px;color:#333;">客票有效期：${inFlight.ticketEffectiveTime}年</span><#if  showTicketCode == "true"> <span style="margin-left:30px;color:#333;">航空公司预订编号：${inFlight.bigCode}</span> </#if></p>
       <#if  showTicketRefundChange == "true"> 
       <p style="display:table;margin:10px 0px 0px;"><span style="display:table-cell;width: 33px;color:#666;">退票&#xa0;</span><span style="display:table-cell;color:#333;">${inFlight.intFlightRefundRule}</span></p> 
       <p style="display:table;margin:10px 0px 0px;"><span style="display:table-cell;width: 33px;color:#666;">改签&#xa0;</span><span style="display:table-cell;color:#333;">${inFlight.intFlightChangeRule}</span></p> 
       <p style="display:table;margin:10px 0px 0px;"><span style="display:table-cell;width: 33px;color:#666;">签转&#xa0;</span><span style="display:table-cell;color:#333;">${inFlight.signRule}</span></p>
       </#if>
      </div> 
     </div>
     <#if inFlight.violationContent!=""> 
     <div style="margin-top: 15px;"> 
      <#if showViolationTravelPolicy=="true" && showViolationReason=="true">
      <div style="padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
       违规信息
      </div> 
      <div style="margin-left:8px;">
       <#if showViolationTravelPolicy=="true"> 
       <p style="margin:10px 0px 0px;"><span style="color:#666;">违反差旅政策：</span><span style="color:#333;">${inFlight.violationContent}</span></p> 
       </#if>
       <#if showViolationReason=="true">
       <p style="margin:10px 0px 0px;"><span style="color:#666;">违规原因：</span><span style="color:#333;">${inFlight.violationReason}</span></p>
       </#if> 
      </div>
      </#if> 
     </div> 
     </#if> 
    </div> 
   </div>
   </#list>
   </#if>
   <#if showSinglePrice=="true">
   <div class="product-cont" style="margin-top:0px;padding: 0px 0px 18px 0px;font-size:10px;background-color:#f9f9f9;"> 
    <div style="margin-left:5px;padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
     价格信息
    </div> 
    <!-- <ul style="margin-top: 10px;">
                    <li></li>
                </ul> --> 
    <div class="flight-content" style="margin: 0px 5px;"> 
     <#if  showFlight == "1">    
     <table style="width:100%;margin-top: 10px;text-align: left;border-bottom: 1px solid #d1dae0;"> 
      <tbody>
       <tr style="height: 30px;background-color: #cfd8de;"> 
        <td style="width:67px;text-align: center;padding: 8px 0px;">国内机票</td> 
        <td colspan="2" style="color: #ff5346;padding: 8px 0px;">￥${flightTotalAmount}</td> 
       </tr>
       <#list flights as flight> 
       <tr style="height: 30px;border-bottom: 1px solid #d1dae0;"> 
        <td></td> 
        <td style="width:240px;height: 30px;">${flight.depDate}出发 ${flight.depCity}——${flight.arrCity}</td> 
        <td style="height: 30px;">￥${flight.totalCharge}（票价:￥${flight.flightAmount}<#if flight.taxCharge!=0>税费:￥${flight.taxCharge}</#if><#if showServiceCharge=="true"><#if flight.serviceCharge!=0>服务费:￥${flight.serviceCharge}</#if></#if> <#if showInsurance=="true">${flight.insurance}</#if> ）</td> 
       </tr>
       </#list> 
      </tbody>
     </table> 
     </#if>
     <#if  showIntFlight == "1">   
     <table style="width:100%;margin-top: 10px;text-align: left;border-collapse: collapse;border-spacing: 0;border-bottom: 1px solid #d1dae0;"> 
      <tbody>
       <tr style="height: 30px;background-color: #cfd8de;"> 
        <td style="width:67px;text-align: center;padding: 8px 0px;">国际机票</td> 
        <td colspan="2" style="color: #ff5346;padding: 8px 0px;">￥${intFlightAmount}</td> 
       </tr>
       <#list intFlights as inFlight>
       <tr style="height: 30px;border-bottom: 1px solid #d1dae0;"> 
        <td></td> 
        <td style="width:240px;height: 30px;">${inFlight.startTime}出发 ${inFlight.startCity}——${inFlight.endCity}（${inFlight.isOverCheng}）</td> 
        <td style="height: 30px;">￥${inFlight.totalFree}（票价:￥${inFlight.ticketPrice}<#if inFlight.taxFee!=0>税费:￥${inFlight.taxFee}</#if><#if showServiceCharge=="true"><#if inFlight.serviceFee !=0>服务费:￥${inFlight.serviceFee}</#if></#if>）</td> 
       </tr>
       </#list> 
      </tbody>
     </table>
     </#if>
     <#if  showHotel == "1">
     <table style="width:100%;margin-top: 10px;text-align: left;border-collapse: collapse;border-spacing: 0;border-bottom: 1px solid #d1dae0;"> 
      <tbody>
       <tr style="height: 30px;background-color: #cfd8de;"> 
        <td style="width:67px;text-align: center;padding: 8px 0px;">酒店</td> 
        <td colspan="2" style="color: #ff5346;padding: 8px 0px;">￥${hotelTotalAmount}</td> 
       </tr>
       <#list hotels as hotel> 
       <tr style="height: 30px;border-bottom: 1px solid #d1dae0;"> 
        <td></td> 
        <td style="width:240px;height: 30px;">${hotel.gmtOccupancyTime}入住 ${hotel.cityName}，${hotel.hotelName}</td> 
        <td style="height: 30px;">￥${hotel.totalCharge}（房价:￥${hotel.roomAmount}<#if hotel.taxCharge!=0>税费:￥${hotel.taxCharge}</#if><#if showServiceCharge=="true"><#if hotel.serviceCharge!=0>服务费:￥${hotel.serviceCharge}</#if></#if>）</td> 
       </tr>
       </#list>  
      </tbody>
     </table>
     </#if>
     <#if  showTrain == "1">     
     <table style="width:100%;margin-top: 10px;text-align: left;border-collapse: collapse;border-spacing: 0;border-bottom: 1px solid #d1dae0;"> 
      <tbody>
       <tr style="height: 30px;background-color: #cfd8de;"> 
        <td style="width:67px;text-align: center;padding: 8px 0px;">火车票</td> 
        <td colspan="2" style="color: #ff5346;padding: 8px 0px;">￥${trainTotalAmount}</td> 
       </tr>
       <#list trains as train>
       <tr style="height: 30px;border-bottom: 1px solid #d1dae0;"> 
        <td></td> 
        <td style="width:240px;height: 30px;">${train.planBeginDate}出发 ${train.depCity}——${train.arrCity}</td> 
        <td style="height: 30px;">￥${train.totalCharge}（票价:￥${train.ticketAmount} <#if showServiceCharge=="true"><#if train.serviceCharge!=0>服务费:￥${train.serviceCharge} </#if></#if>）</td> 
       </tr>
       </#list> 
      </tbody>
     </table>
     </#if>
    </div>
    <p style="margin:10px 0px 0px;"><span style="color:#333;font-weight:bold;">行程总计：</span><span style="color: #ff5346;">￥${itineraryTotalAmount}</span>&#xa0;<span style="color:#333;font-weight:bold;">节省：</span><span style="color: #ff5346;">￥${saveTotalAmount}</span>&#xa0;<span style="color:#333;font-weight:bold;">损失：</span><span style="color: #ff5346;">￥${lossTotalAmount}</span></p>  
   </div>
   </#if> 
   <div class="product-cont" style="margin-top:0px;padding: 0px 0px 18px 0px;font-size:10px;background-color:#f9f9f9;">
   <#if  itineraryRemark != ""> 
    <div style="margin-left:5px;padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
     行程备注
    </div> 
    <p style="margin-left:13px;">${itineraryRemark}</p> 
    </#if>
    <div style="margin-left:5px;padding-left:5px;border-left:2px solid #7591a7;color:#333;font-weight:bold;">
     温馨提示
    </div> 
    <div style="list-style: none;padding:0px 10px;color:#666;">                                              
     <#if tmcId == 5><p style="margin-bottom:5px;" >1.大唐商旅为您提供一站式差旅服务，如您有机票，酒店，火车票，保险，MICE等所有与旅行相关的需求，请联系您的旅行顾问，咨询邮箱：Eric.xu@dttrip.com</p></#if>
     <#if tmcId != 5><p style="margin-bottom:5px;" >1.同程商旅为您提供一站式差旅服务，如您有机票，酒店，火车票，租车，保险，MICE等所有与旅行相关的需求，请联系您的旅行顾问，咨询邮箱：tmcsrv@ly.com</p></#if> 
     <p style="margin-bottom:5px;">2.当您在机场遇到任何问题的时候尽量不要自行付费处理，请先联系您的旅行顾问。</p> 
     <p style="margin-bottom:5px;">3.国内航班请于出发前90分钟办理登机手续。</p> 
     <p style="margin-bottom:5px;">4.国际航班请于出发前3小时办理登机手续。</p> 
     <p style="margin-bottom:5px;">5.如您对我们的服务有任何的建议和意见，您可以随时通过电话等方式联系我们。非常感谢。</p>
    </div> 
   </div> 
  </div>   
 </body>
</html>