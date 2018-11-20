$(function() {
  $('#uploadKcbGrid tbody').on('click', 'button#deleteKcbFile', function() {
	var data = $('#uploadKcbGrid').DataTable().row($(this).parents('tr')).data();
	$('#uploadKcbConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#uploadKcbConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-kcb-file/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#uploadKcbGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#uploadKcbGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#recordsKcbGrid tbody').on('click', 'button#viewKcb', function() {
	var data = $('#recordsKcbGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#recordsKcbViewTitle').html(data.customerNo);
	$('#recordsId').html(data.id);
    $('#recordsFileId').html(data.fileId);
    $('#recordsRecordNo').html(data.recordNo);
    $('#recordsCustomerNo').html(data.customerNo);
    $('#recordsMainTransCode').html(data.mainTransCode);
    $('#recordsValueX1').html(data.valueX1);
    $('#recordsAccountType').html(data.accountType);
    $('#recordsTransCode').html(data.transCode);
    $('#recordsIncomingDate').html(data.incomingDate);
    $('#recordsIncomingTime').html(data.incomingTime);
    $('#recordsTransRef').html(data.transRef);
    $('#recordsAccountNo').html(data.accountNo);
    $('#recordsNarration').html(data.narration);
    $('#recordsDepositDao').html(data.depositDao);
    $('#recordsValueX2').html(data.valueX2);
    $('#recordsValueX3').html(data.valueX3);
    $('#recordsValueX4').html(data.valueX4);
    $('#recordsDrValue').html(data.drValue);
    $('#recordsValueX5').html(data.valueX5);
    $('#recordsAmount1').html(data.amount1);
    $('#recordsAmount2').html(data.amount2);
    $('#recordsCurrency').html(data.currency);
    $('#recordsMatched').html(data.matched);
    $('#recordsCheckFlag').html(data.checkFlag);
    $('#recordsPointsEarned').html(data.pointsEarned);
    $('#recordsClosed').html(data.closed);
    $('#recordsSuccessErrorFlag').html(data.successErrorFlag);
    $('#recordsGenerated').html(data.generated);
    $('#recordsProcessed').html(data.processed);
    $('#recordsReturned').html(data.returned);
    $('#recordsKcbView').modal('show');
  });
  
  $('#recordsKcbGrid tbody').on('click', 'button#editKcb', function() {
	var data = $('#recordsKcbGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#recordsKcbEditTitle').html(data.customerNo);
	$('#id').val(data.id);
    $('#fileId').val(data.fileId);
    $('#fileId').attr('value', data.fileId);
    $('#recordNo').val(data.recordNo);
    $('#recordNo').attr('value', data.recordNo);
    $('#customerNo').val(data.customerNo);
    $('#customerNo').attr('value', data.customerNo);
    $('#mainTransCode').val(data.mainTransCode);
    $('#mainTransCode').attr('value', data.mainTransCode);
    $('#valueX1').val(data.valueX1);
    $('#valueX1').attr('value', data.valueX1);
    $('#accountType').val(data.accountType);
    $('#accountType').attr('value', data.accountType);
    $('#transCode').val(data.transCode);
    $('#transCode').attr('value', data.transCode);
    $('#incomingDate').val(data.incomingDate);
    $('#incomingDate').attr('value', data.incomingDate);
    $('#incomingTime').val(data.incomingTime);
    $('#incomingTime').attr('value', data.incomingTime);
    $('#transRef').val(data.transRef);
    $('#transRef').attr('value', data.transRef);
    $('#accountNo').val(data.accountNo);
    $('#accountNo').attr('value', data.accountNo);
    $('#narration').val(data.narration);
    $('#narration').attr('value', data.narration);
    $('#depositDao').val(data.depositDao);
    $('#depositDao').attr('value', data.depositDao);
    $('#valueX2').val(data.valueX2);
    $('#valueX2').attr('value', data.valueX2);
    $('#valueX3').val(data.valueX3);
    $('#valueX3').attr('value', data.valueX3);
    $('#valueX4').val(data.valueX4);
    $('#valueX4').attr('value', data.valueX4);
    $('#drValue').val(data.drValue);
    $('#drValue').attr('value', data.drValue);
    $('#valueX5').val(data.valueX5);
    $('#valueX5').attr('value', data.valueX5);
    $('#amount1').val(data.amount1);
    $('#amount1').attr('value', data.amount1);
    $('#amount2').val(data.amount2);
    $('#amount2').attr('value', data.amount2);
    $('#currency').val(data.currency);
    $('#currency').attr('value', data.currency);
    $('#matched').val(data.matched);
    $('#matched').attr('value', data.matched);
    $('#checkFlag').val(data.checkFlag);
    $('#checkFlag').attr('value', data.checkFlag);
    $('#pointsEarned').val(data.pointsEarned);
    $('#pointsEarned').attr('value', data.pointsEarned);
    $('#closed').val(data.closed);
    $('#closed').attr('value', data.closed);
    $('#successErrorFlag').val(data.successErrorFlag);
    $('#successErrorFlag').attr('value', data.successErrorFlag);
    $('#generated').val(data.generated);
    $('#generated').attr('value', data.generated);
    $('#processed').val(data.processed);
    $('#processed').attr('value', data.processed);
    $('#returned').val(data.returned);
    $('#returned').attr('value', data.returned);
    $('#recordsKcbEdit').modal('show');
  });
  
  $('#recordsKcbGrid tbody').on('click', 'button#deleteKcb', function() {
	var data = $('#recordsKcbGrid').DataTable().row($(this).parents('tr')).data();
	$('#recordsKcbConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#recordsKcbConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-kcb-record/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#recordsKcbGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#recordsKcbGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#uploadInfiniaGrid tbody').on('click', 'button#deleteInfiniaFile', function() {
	var data = $('#uploadInfiniaGrid').DataTable().row($(this).parents('tr')).data();
	$('#uploadInfiniaConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#uploadInfiniaConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-infinia-file/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#uploadInfiniaGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#uploadInfiniaGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#recordsInfiniaGrid tbody').on('click', 'button#viewInfinia', function() {
	var data = $('#recordsInfiniaGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#recordsInfiniaViewTitle').html(data.customerNo);
	$('#recordsId').html(data.id);
    $('#recordsFileId').html(data.fileId);
    $('#recordsRecordNo').html(data.recordNo);
    $('#recordsCustomerNo').html(data.customerNo);
    $('#recordsMainTransCode').html(data.mainTransCode);
    $('#recordsValueX1').html(data.valueX1);
    $('#recordsAccountType').html(data.accountType);
    $('#recordsTransCode').html(data.transCode);
    $('#recordsIncomingDate').html(data.incomingDate);
    $('#recordsIncomingTime').html(data.incomingTime);
    $('#recordsTransRef').html(data.transRef);
    $('#recordsAccountNo').html(data.accountNo);
    $('#recordsNarration').html(data.narration);
    $('#recordsDepositDao').html(data.depositDao);
    $('#recordsValueX2').html(data.valueX2);
    $('#recordsValueX3').html(data.valueX3);
    $('#recordsValueX4').html(data.valueX4);
    $('#recordsDrValue').html(data.drValue);
    $('#recordsValueX5').html(data.valueX5);
    $('#recordsAmount1').html(data.amount1);
    $('#recordsAmount2').html(data.amount2);
    $('#recordsCurrency').html(data.currency);
    $('#recordsValueX6').html(data.valueX6);
    $('#recordsValueX7').html(data.valueX7);
    $('#recordsPointsEarned').html(data.pointsEarned);
    $('#recordsSuccessErrorFlag').html(data.successErrorFlag);
    $('#recordsMatched').html(data.matched);
    $('#recordsCheckFlag').html(data.checkFlag);
    $('#recordsClosed').html(data.closed);
    $('#recordsErrorCode').html(data.errorCode);
    $('#recordsGenerated').html(data.generated);
    $('#recordsProcessed').html(data.processed);
    $('#recordsReturned').html(data.returned);
    $('#recordsInfiniaView').modal('show');
  });
  
  $('#recordsInfiniaGrid tbody').on('click', 'button#editInfinia', function() {
	var data = $('#recordsInfiniaGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#recordsInfiniaEditTitle').html(data.customerNo);
	$('#id').val(data.id);
    $('#fileId').val(data.fileId);
    $('#fileId').attr('value', data.fileId);
    $('#recordNo').val(data.recordNo);
    $('#recordNo').attr('value', data.recordNo);
    $('#customerNo').val(data.customerNo);
    $('#customerNo').attr('value', data.customerNo);
    $('#mainTransCode').val(data.mainTransCode);
    $('#mainTransCode').attr('value', data.mainTransCode);
    $('#valueX1').val(data.valueX1);
    $('#valueX1').attr('value', data.valueX1);
    $('#accountType').val(data.accountType);
    $('#accountType').attr('value', data.accountType);
    $('#transCode').val(data.transCode);
    $('#transCode').attr('value', data.transCode);
    $('#incomingDate').val(data.incomingDate);
    $('#incomingDate').attr('value', data.incomingDate);
    $('#incomingTime').val(data.incomingTime);
    $('#incomingTime').attr('value', data.incomingTime);
    $('#transRef').val(data.transRef);
    $('#transRef').attr('value', data.transRef);
    $('#accountNo').val(data.accountNo);
    $('#accountNo').attr('value', data.accountNo);
    $('#narration').val(data.narration);
    $('#narration').attr('value', data.narration);
    $('#depositDao').val(data.depositDao);
    $('#depositDao').attr('value', data.depositDao);
    $('#valueX2').val(data.valueX2);
    $('#valueX2').attr('value', data.valueX2);
    $('#valueX3').val(data.valueX3);
    $('#valueX3').attr('value', data.valueX3);
    $('#valueX4').val(data.valueX4);
    $('#valueX4').attr('value', data.valueX4);
    $('#drValue').val(data.drValue);
    $('#drValue').attr('value', data.drValue);
    $('#valueX5').val(data.valueX5);
    $('#valueX5').attr('value', data.valueX5);
    $('#amount1').val(data.amount1);
    $('#amount1').attr('value', data.amount1);
    $('#amount2').val(data.amount2);
    $('#amount2').attr('value', data.amount2);
    $('#currency').val(data.currency);
    $('#currency').attr('value', data.currency);
    $('#valueX6').val(data.valueX6);
    $('#valueX6').attr('value', data.valueX6);
    $('#valueX7').val(data.valueX7);
    $('#valueX7').attr('value', data.valueX7);
    $('#pointsEarned').val(data.pointsEarned);
    $('#pointsEarned').attr('value', data.pointsEarned);
    $('#successErrorFlag').val(data.successErrorFlag);
    $('#successErrorFlag').attr('value', data.successErrorFlag);
    $('#matched').val(data.matched);
    $('#matched').attr('value', data.matched);
    $('#checkFlag').val(data.checkFlag);
    $('#checkFlag').attr('value', data.checkFlag);
    $('#closed').val(data.closed);
    $('#closed').attr('value', data.closed);
    $('#errorCode').val(data.errorCode);
    $('#errorCode').attr('value', data.errorCode);
    $('#generated').val(data.generated);
    $('#generated').attr('value', data.generated);
    $('#processed').val(data.processed);
    $('#processed').attr('value', data.processed);
    $('#returned').val(data.returned);
    $('#returned').attr('value', data.returned);
    $('#recordsInfiniaEdit').modal('show');
  });
  
  $('#recordsInfiniaGrid tbody').on('click', 'button#deleteInfinia', function() {
	var data = $('#recordsInfiniaGrid').DataTable().row($(this).parents('tr')).data();
	$('#recordsInfiniaConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#recordsInfiniaConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-infinia-record/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#recordsInfiniaGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#recordsInfiniaGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#uploadCustomerGrid tbody').on('click', 'button#deleteCustomerFile', function() {
	var data = $('#uploadCustomerGrid').DataTable().row($(this).parents('tr')).data();
	$('#uploadCustomerConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#uploadCustomerConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-customer-file/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#uploadCustomerGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#uploadCustomerGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#recordsCustomerGrid tbody').on('click', 'button#viewCustomer', function() {
	var data = $('#recordsCustomerGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#recordsCustomerViewTitle').html(data.name);
	$('#recordsId').html(data.id);
    $('#recordsCustomerCode').html(data.customerCode);
    $('#recordsMnemonic').html(data.mnemonic);
    $('#recordsShortName').html(data.shortName);
    $('#recordsName').html(data.name);
    $('#recordsOptInDate').html(data.optInDate);
    $('#recordsStreet').html(data.street);
    $('#recordsAddress').html(data.address);
    $('#recordsTown').html(data.town);
    $('#recordsPostCode').html(data.postCode);
    $('#recordsCountry').html(data.country);
    $('#recordsSector').html(data.sector);
    $('#recordsAccountOfficer').html(data.accountOfficer);
    $('#recordsOtherOfficer').html(data.otherOfficer);
    $('#recordsIndustry').html(data.industry);
    $('#recordsTarget').html(data.target);
    $('#recordsNationality').html(data.nationality);
    $('#recordsCustomerStatus').html(data.customerStatus);
    $('#recordsResidence').html(data.residence);
    $('#recordsContactDate').html(data.contactDate);
    $('#recordsCustOffice').html(data.custOffice);
    $('#recordsCustMobile').html(data.custMobile);
    $('#recordsCustomerView').modal('show');
  });
  
  $('#recordsCustomerGrid tbody').on('click', 'button#editCustomer', function() {
	var data = $('#recordsCustomerGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#recordsCustomerEditTitle').html(data.name);
	$('#id').val(data.id);
    $('#customerCode').val(data.customerCode);
    $('#customerCode').attr('value', data.customerCode);
    $('#mnemonic').val(data.mnemonic);
    $('#mnemonic').attr('value', data.mnemonic);
    $('#shortName').val(data.shortName);
    $('#shortName').attr('value', data.shortName);
    $('#name').val(data.name);
    $('#name').attr('value', data.name);
    $('#optInDate').val(data.optInDate);
    $('#optInDate').attr('value', data.optInDate);
    $('#street').val(data.street);
    $('#street').attr('value', data.street);
    $('#address').val(data.address);
    $('#address').attr('value', data.address);
    $('#town').val(data.town);
    $('#town').attr('value', data.town);
    $('#postCode').val(data.postCode);
    $('#postCode').attr('value', data.postCode);
    $('#country').val(data.country);
    $('#country').attr('value', data.country);
    $('#sector').val(data.sector);
    $('#sector').attr('value', data.sector);
    $('#accountOfficer').val(data.accountOfficer);
    $('#accountOfficer').attr('value', data.accountOfficer);
    $('#otherOfficer').val(data.otherOfficer);
    $('#otherOfficer').attr('value', data.otherOfficer);
    $('#industry').val(data.industry);
    $('#industry').attr('value', data.industry);
    $('#target').val(data.target);
    $('#target').attr('value', data.target);
    $('#nationality').val(data.nationality);
    $('#nationality').attr('value', data.nationality);
    $('#customerStatus').val(data.customerStatus);
    $('#customerStatus').attr('value', data.customerStatus);
    $('#residence').val(data.residence);
    $('#residence').attr('value', data.residence);
    $('#contactDate').val(data.contactDate);
    $('#contactDate').attr('value', data.contactDate);
    $('#custOffice').val(data.custOffice);
    $('#custOffice').attr('value', data.custOffice);
    $('#custMobile').val(data.custMobile);
    $('#custMobile').attr('value', data.custMobile);
    $('#recordsCustomerEdit').modal('show');
  });
  
  $('#recordsCustomerGrid tbody').on('click', 'button#deleteCustomer', function() {
	var data = $('#recordsCustomerGrid').DataTable().row($(this).parents('tr')).data();
	$('#recordsCustomerConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#recordsCustomerConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-customer-record/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#recordsCustomerGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#recordsCustomerGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#currencyGrid tbody').on('click', 'button#viewCurrency', function() {
	var data = $('#currencyGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#currencyViewTitle').html(data.abbreviation + " - " + data.name);
	$('#currencyId').html(data.id);
    $('#currencyCodeNumber').html(data.codeNumber);
    $('#currencyAbbreviation').html(data.abbreviation);
    $('#currencyName').html(data.name);
    $('#currencyView').modal('show');
  });
  
  $('#currencyGrid tbody').on('click', 'button#editCurrency', function() {
	var data = $('#currencyGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#currencyEditTitle').html(data.abbreviation + " - " + data.name);
	$('#id').val(data.id);
    $('#codeNumber').val(data.codeNumber);
    $('#codeNumber').attr('value', data.codeNumber);
    $('#abbreviation').val(data.abbreviation);
    $('#abbreviation').attr('value', data.abbreviation);
    $('#name').val(data.name);
    $('#name').attr('value', data.name);
    $('#currencyEdit').modal('show');
  });
  
  $('#currencyGrid tbody').on('click', 'button#deleteCurrency', function() {
	var data = $('#currencyGrid').DataTable().row($(this).parents('tr')).data();
	$('#currencyConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#currencyConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-currency/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#currencyGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#currencyGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#productCodeGrid tbody').on('click', 'button#viewProductCode', function() {
	var data = $('#productCodeGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#productCodeViewTitle').html(data.code + " - " + data.description);
	$('#productCodeId').html(data.id);
    $('#productCodeCode').html(data.code);
    $('#productCodeDescription').html(data.description);
    $('#productCodeView').modal('show');
  });
  
  $('#productCodeGrid tbody').on('click', 'button#editProductCode', function() {
	var data = $('#productCodeGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#productCodeEditTitle').html(data.code + " - " + data.description);
	$('#id').val(data.id);
    $('#code').val(data.code);
    $('#code').attr('value', data.code);
    $('#description').val(data.description);
    $('#description').attr('value', data.description);
    $('#productCodeEdit').modal('show');
  });
  
  $('#productCodeGrid tbody').on('click', 'button#deleteProductCode', function() {
	var data = $('#productCodeGrid').DataTable().row($(this).parents('tr')).data();
	$('#productCodeConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#productCodeConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-product-code/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#productCodeGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#productCodeGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#channelGrid tbody').on('click', 'button#viewChannel', function() {
	var data = $('#channelGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#channelViewTitle').html(data.code + " - " + data.category + ", " + data.subCategory);
	$('#channelId').html(data.id);
    $('#channelCode').html(data.code);
    $('#channelCategory').html(data.category);
    $('#channelSubCategory').html(data.subCategory);
    $('#channelDealFlag').html(data.dealFlag);
    $('#channelActive').html(data.active);
    $('#channelView').modal('show');
  });
  
  $('#channelGrid tbody').on('click', 'button#editChannel', function() {
	var data = $('#channelGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#channelEditTitle').html(data.code + " - " + data.category + ", " + data.subCategory);
	$('#id').attr('value', data.id);
    $('#code option[value="'+data.code+'"]').attr('selected', true);
    $('#subCategory').attr('value', data.subCategory);
    
    codeCategories(data.code, data.category);
    
    $('#channelEdit').modal('show');
  });
  
  $('#channelEdit').on('hidden.bs.modal', function(e) {
	$('#id').removeAttr('value');
	$('#code option').removeAttr('selected');
	$('#category option').remove();
  });
  
  $('#channelGrid tbody').on('click', 'button#deleteChannel', function() {
	var data = $('#channelGrid').DataTable().row($(this).parents('tr')).data();
	$('#channelConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#channelConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-channel/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#channelGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#channelGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#pointsMatrixGrid tbody').on('click', 'button#viewPoints', function() {
	var data = $('#pointsMatrixGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#pointsViewTitle').html(data.channelCode + " - " + data.channelName + ", " + data.productName);
	$('#pointsId').html(data.id);
    $('#pointsChannelName').html(data.channelName);
    $('#pointsChannelCode').html(data.channelCode);
    $('#pointsProductName').html(data.productName);
    $('#pointsProductCode').html(data.productCode);
    $('#pointsDealCode').html(data.dealCode);
    $('#pointsPos').html(data.pos);
    $('#pointsMinDay').html(data.minDay);
    $('#pointsMaxDay').html(data.maxDay);
    $('#pointsPointsValue').html(data.pointsValue);
    $('#pointsPointsAmount').html(data.pointsAmount);
    $('#pointsOnlineTransCode').html(data.onlineTransCode);
    $('#pointsIntlTransCode').html(data.intlTransCode);
    $('#pointsPosTxnCode').html(data.posTxnCode);
    $('#pointsCurrency').html(data.currency);
    $('#pointsView').modal('show');
  });
  
  $('#pointsMatrixGrid tbody').on('click', 'button#editPoints', function() {
	var data = $('#pointsMatrixGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#pointsEditTitle').html(data.channelCode + " - " + data.channelName + ", " + data.productName);
	$('#id').val(data.id);
    $('#channelCode option[value="'+data.channelCode+" - "+data.channelName+'"]').attr('selected', true);
    $('#productCode').val(data.productCode);
    $('#productCode').attr('value', data.productCode);
    $('#dealCode').val(data.dealCode);
    $('#dealCode').attr('value', data.dealCode);
    $('#minDay').val(data.minDay);
    $('#minDay').attr('value', data.minDay);
    $('#maxDay').val(data.maxDay);
    $('#maxDay').attr('value', data.maxDay);
    $('#pointsValue').val(data.pointsValue);
    $('#pointsValue').attr('value', data.pointsValue);
    $('#pointsAmount').val(data.pointsAmount);
    $('#pointsAmount').attr('value', data.pointsAmount);
    $('#onlineTransCode').val(data.onlineTransCode);
    $('#onlineTransCode').attr('value', data.onlineTransCode);
    $('#intlTransCode').val(data.intlTransCode);
    $('#intlTransCode').attr('value', data.intlTransCode);
    $('#posTxnCode').val(data.posTxnCode);
    $('#posTxnCode').attr('value', data.posTxnCode);
    
    if (data.pos == 'KCB') {
      $('#pos1').prop('checked', true);
      $('#pos1').attr('checked', true);
    }
    if (data.pos == 'Non-KCB') {
      $('#pos2').prop('checked', true);
      $('#pos2').attr('checked', true);
    }
    
    $.get('./points-currencies/' + data.currency, function(result) {
      $('#currency').val(result[0]);
      $('#currency option[value="'+result[0]+'"]').attr('selected', true);
    });
    
    $('#pointsEdit').modal('show');
  });
  
  $('#pointsMatrixGrid tbody').on('click', 'button#deletePoints', function() {
	var data = $('#pointsMatrixGrid').DataTable().row($(this).parents('tr')).data();
	$('#pointsConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#pointsConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-points/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#pointsMatrixGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#pointsMatrixGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#daoGrid tbody').on('click', 'button#viewDao', function() {
	var data = $('#daoGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#daoViewTitle').html(data.branchCode + " - " + data.branchName);
	$('#daoId').html(data.id);
    $('#daoBranchCode').html(data.branchCode);
    $('#daoBranchName').html(data.branchName);
    $('#daoProfitLossAc').html(data.profitLossAc);
    $('#daoLoyaltyAc').html(data.loyaltyAc);
    $('#daoHoldingAc').html(data.holdingAc);
    $('#daoView').modal('show');
  });
  
  $('#daoGrid tbody').on('click', 'button#editDao', function() {
	var data = $('#daoGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#daoEditTitle').html(data.branchCode + " - " + data.branchName);
	$('#id').val(data.id);
    $('#branchCode').val(data.branchCode);
    $('#branchCode').attr('value', data.branchCode);
    $('#branchName').val(data.branchName);
    $('#branchName').attr('value', data.branchName);
    $('#profitLossAc').val(data.profitLossAc);
    $('#profitLossAc').attr('value', data.profitLossAc);
    $('#loyaltyAc').val(data.loyaltyAc);
    $('#loyaltyAc').attr('value', data.loyaltyAc);
    $('#holdingAc').val(data.holdingAc);
    $('#holdingAc').attr('value', data.holdingAc);
    $('#daoEdit').modal('show');
  });
  
  $('#daoGrid tbody').on('click', 'button#deleteDao', function() {
	var data = $('#daoGrid').DataTable().row($(this).parents('tr')).data();
	$('#daoConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#daoConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-dao/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#daoGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#daoGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#transactionCodeGrid tbody').on('click', 'button#viewTransactionCode', function() {
	var data = $('#transactionCodeGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#transactionCodeViewTitle').html(data.code + " - " + data.description);
	$('#transactionCodeId').html(data.id);
    $('#transactionCodeCode').html(data.code);
    $('#transactionCodeDescription').html(data.description);
    $('#transactionCodeView').modal('show');
  });
  
  $('#transactionCodeGrid tbody').on('click', 'button#editTransactionCode', function() {
	var data = $('#transactionCodeGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#transactionCodeEditTitle').html(data.code + " - " + data.description);
	$('#id').val(data.id);
    $('#code').val(data.code);
    $('#code').attr('value', data.code);
    $('#description').val(data.description);
    $('#description').attr('value', data.description);
    $('#transactionCodeEdit').modal('show');
  });
  
  $('#transactionCodeGrid tbody').on('click', 'button#deleteTransactionCode', function() {
	var data = $('#transactionCodeGrid').DataTable().row($(this).parents('tr')).data();
	$('#transactionCodeConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#transactionCodeConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-transaction-code/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#transactionCodeGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#transactionCodeGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#reasonCodeGrid tbody').on('click', 'button#viewReasonCode', function() {
	var data = $('#reasonCodeGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#reasonCodeViewTitle').html(data.code + " - " + data.description);
	$('#reasonCodeId').html(data.id);
    $('#reasonCodeCode').html(data.code);
    $('#reasonCodeDescription').html(data.description);
    $('#reasonCodeView').modal('show');
  });
  
  $('#reasonCodeGrid tbody').on('click', 'button#editReasonCode', function() {
	var data = $('#reasonCodeGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#reasonCodeEditTitle').html(data.code + " - " + data.description);
	$('#id').val(data.id);
    $('#code').val(data.code);
    $('#code').attr('value', data.code);
    $('#description').val(data.description);
    $('#description').attr('value', data.description);
    $('#reasonCodeEdit').modal('show');
  });
  
  $('#reasonCodeGrid tbody').on('click', 'button#deleteReasonCode', function() {
	var data = $('#reasonCodeGrid').DataTable().row($(this).parents('tr')).data();
	$('#reasonCodeConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#reasonCodeConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-reason-code/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#reasonCodeGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#reasonCodeGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#directoryGrid tbody').on('click', 'button#viewDirectory', function() {
	var data = $('#directoryGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#directoryViewTitle').html(data.kcbInDir + " - " + data.infiniaInDir + " - " + data.customerInDir);
	$('#directoryId').html(data.id);
    $('#directoryKcbInDir').html(data.kcbInDir);
    $('#directoryKcbOutDir').html(data.kcbOutDir);
    $('#directoryInfiniaInDir').html(data.infiniaInDir);
    $('#directoryInfiniaOutDir').html(data.infiniaOutDir);
    $('#directoryCustomerInDir').html(data.customerInDir);
    $('#directoryCustomerOutDir').html(data.customerOutDir);
    $('#directoryKcbBkDir').html(data.kcbBkDir);
    $('#directoryInfiniaBkDir').html(data.infiniaBkDir);
    $('#directoryCustomerBkDir').html(data.customerBkDir);
    $('#directoryView').modal('show');
  });
  
  $('#directoryGrid tbody').on('click', 'button#editDirectory', function() {
	var data = $('#directoryGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#directoryEditTitle').html(data.kcbInDir + " - " + data.infiniaInDir + " - " + data.customerInDir);
	$('#id').val(data.id);
    $('#kcbInDir').val(data.kcbInDir);
    $('#kcbInDir').attr('value', data.kcbInDir);
    $('#kcbOutDir').val(data.kcbOutDir);
    $('#kcbOutDir').attr('value', data.kcbOutDir);
    $('#infiniaInDir').val(data.infiniaInDir);
    $('#infiniaInDir').attr('value', data.infiniaInDir);
    $('#infiniaOutDir').val(data.infiniaOutDir);
    $('#infiniaOutDir').attr('value', data.infiniaOutDir);
    $('#customerInDir').val(data.customerInDir);
    $('#customerInDir').attr('value', data.customerInDir);
    $('#customerOutDir').val(data.customerOutDir);
    $('#customerOutDir').attr('value', data.customerOutDir);
    $('#kcbBkDir').val(data.kcbBkDir);
    $('#kcbBkDir').attr('value', data.kcbBkDir);
    $('#infiniaBkDir').val(data.infiniaBkDir);
    $('#infiniaBkDir').attr('value', data.infiniaBkDir);
    $('#customerBkDir').val(data.customerBkDir);
    $('#customerBkDir').attr('value', data.customerBkDir);
    $('#directoryEdit').modal('show');
  });
  
  $('#merchantGrid tbody').on('click', 'button#viewMerchant', function() {
	var data = $('#merchantGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#merchantViewTitle').html(data.name);
	$('#merchantId').html(data.id);
    $('#merchantMId').html(data.mId);
    $('#merchantCode').html('********');
    $('#merchantName').html(data.name);
    $('#merchantPassword').html('********');
    $('#merchantView').modal('show');
  });
  
  $('#merchantGrid tbody').on('click', 'button#editMerchant', function() {
	var data = $('#merchantGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#merchantEditTitle').html(data.name);
	$('#id').val(data.id);
    $('#mId').val(data.mId);
    $('#mId').attr('value', data.mId);
    $('#code').val(data.code);
    $('#code').attr('value', data.code);
    $('#name').val(data.name);
    $('#name').attr('value', data.name);
    $('#password').val(data.password);
    $('#password').attr('value', data.password);
    $('#merchantEdit').modal('show');
  });
  
  $('#merchantGrid tbody').on('click', 'button#deleteMerchant', function() {
	var data = $('#merchantGrid').DataTable().row($(this).parents('tr')).data();
	$('#merchantConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#merchantConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-merchant/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#merchantGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#merchantGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#transSummaryGrid tbody').on('click', 'button#viewTransSummary', function() {
	var data = $('#transSummaryGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#transSummaryViewTitle').html(data.kcbFileId + " - " + data.infiniaFileId);
	$('#transId').html(data.id);
    $('#transInfiniaFileId').html(data.infiniaFileId);
    $('#transKcbFileId').html(data.kcbFileId);
    $('#transInfiniaFileCount').html(data.infiniaFileCount);
    $('#transKcbFileCount').html(data.kcbFileCount);
    $('#transCountMatched').html(data.countMatched);
    /*$('#transAllCustomersExist').html(data.allCustomersExist);*/
    $('#transNonCustomerCountInf').html(data.nonCustomerCountInf);
    $('#transNonCustomerCountKcb').html(data.nonCustomerCountKcb);
    $('#transInfiniaTotalPoints').html(data.infiniaTotalPoints);
    $('#transKcbTotalPoints').html(data.kcbTotalPoints);
    $('#transPointsMatched').html(data.pointsMatched);
    $('#transInfiniaDistinctTxnCode').html(data.infiniaDistinctTxnCode);
    $('#transKcbDistinctTxnCode').html(data.kcbDistinctTxnCode);
    $('#transDistinctTxnCodeMatched').html(data.distinctTxnCodeMatched);
    $('#transInfiniaSuccessCount').html(data.infiniaSuccessCount);
    $('#transKcbSuccessCount').html(data.kcbSuccessCount);
    $('#transSuccessCountMatched').html(data.successCountMatched);
    $('#transInfiniaErrorCount').html(data.infiniaErrorCount);
    $('#transKcbErrorCount').html(data.kcbErrorCount);
    $('#transErrorCountMatched').html(data.errorCountMatched);
    $('#transInfiniaTotalAmount1').html(data.infiniaTotalAmount1);
    $('#transKcbTotalAmount1').html(data.kcbTotalAmount1);
    $('#transAmount1Matched').html(data.amount1Matched);
    $('#transInfiniaTotalAmount2').html(data.infiniaTotalAmount2);
    $('#transKcbTotalAmount2').html(data.kcbTotalAmount2);
    $('#transAmount2Matched').html(data.amount2Matched);
    $('#transFileMatched').html(data.fileMatched);
    $('#transClosed').html(data.closed);
    $('#transSummaryView').modal('show');
  });
  
  $('#redemptionSummaryGrid tbody').on('click', 'button#viewRedemption', function() {
	var data = $('#redemptionSummaryGrid').DataTable().row($(this).parents('tr')).data();
	$('#redemptionSummaryViewTitle').html(data.customerNo);
	
    $('#redemptionDetailsGrid').DataTable({
      destroy: true,
      scrollX: true,
      ajax: {
	    url: './list-redemption-details-grid/' + data.id,
	    dataSrc: ''
	  },
	  columns: [
	    {data: 'id'},
	    {data: 'customerNo'},
	    {data: 'createdDate'},
	    {data: 'points'},
	    {data: 'pointsFlag'},
	    {data: 'fileId'},
	    {data: 'recordNo'},
	    {data: 'transRef'},
	    {data: 'amount'},
	    {data: 'narration1'},
	    {data: 'narration2'},
	    {data: 'description'},
	    {data: 'mobileNo'},
	    {data: 'transDate'},
	    {data: 'transCode'},
	    {data: 'dao'}
	  ],
	  columnDefs: [
        {
          targets: [0],
          visible: false
        }
      ]
    });
	
    $('#redemptionSummaryView').modal('show');
  });
  
  $('#redemptionSummaryView').on('shown.bs.modal', function(e) {
	$('#redemptionDetailsGrid').DataTable().columns.adjust().draw();
  });
  
  $('#daoSummaryGrid tbody').on('click', 'button#viewDaoSummary', function() {
	var data = $('#daoSummaryGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#daoSummaryViewTitle').html(data.dao);
	$('#daoSumId').html(data.id);
    $('#daoSumDao').html(data.dao);
    $('#daoSumAmount1').html(data.amount1);
    $('#daoSumAmount2').html(data.amount2);
    $('#daoSumTotalPoints').html(data.totalPoints);
    $('#daoSumPosting75').html(data.posting75);
    $('#daoSumPosting25').html(data.posting25);
    $('#daoSumProfitLossAc').html(data.profitLossAc);
    $('#daoSumSuspenseAc').html(data.suspenseAc);
    $('#daoSumHoldingAc').html(data.holdingAc);
    $('#daoSumPostingDate').html(data.postingDate);
    $('#daoSumT24Ref').html(data.t24Ref);
    $('#daoSumMessageId').html(data.messageId);
    $('#daoSumFileId').html(data.fileId);
    $('#daoSumDescription').html(data.description);
    $('#daoSumReason').html(data.reason);
    $('#daoSummaryView').modal('show');
  });
  
  $('#daoSummaryGrid tbody').on('click', 'button#editDaoSummary', function() {
	var data = $('#daoSummaryGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#daoSummaryEditTitle').html(data.dao);
	$('#id').val(data.id);
    $('#dao').val(data.dao);
    $('#dao').attr('value', data.dao);
    $('#amount1').val(data.amount1);
    $('#amount1').attr('value', data.amount1);
    $('#amount2').val(data.amount2);
    $('#amount2').attr('value', data.amount2);
    $('#totalPoints').val(data.totalPoints);
    $('#totalPoints').attr('value', data.totalPoints);
    $('#posting75').val(data.posting75);
    $('#posting75').attr('value', data.posting75);
    $('#posting25').val(data.posting25);
    $('#posting25').attr('value', data.posting25);
    $('#profitLossAc').val(data.profitLossAc);
    $('#profitLossAc').attr('value', data.profitLossAc);
    $('#suspenseAc').val(data.suspenseAc);
    $('#suspenseAc').attr('value', data.suspenseAc);
    $('#holdingAc').val(data.holdingAc);
    $('#holdingAc').attr('value', data.holdingAc);
    $('#postingDate').val(data.postingDate);
    $('#postingDate').attr('value', data.postingDate);
    $('#t24Ref').val(data.t24Ref);
    $('#t24Ref').attr('value', data.t24Ref);
    $('#messageId').val(data.messageId);
    $('#messageId').attr('value', data.messageId);
    $('#fileId').val(data.fileId);
    $('#fileId').attr('value', data.fileId);
    $('#description').val(data.description);
    $('#description').attr('value', data.description);
    $('#reason').val(data.reason);
    $('#reason').attr('value', data.reason);
    $('#daoSummaryEdit').modal('show');
  });
  
  $('#daoSummaryGrid tbody').on('click', 'button#deleteDaoSummary', function() {
	var data = $('#daoSummaryGrid').DataTable().row($(this).parents('tr')).data();
	$('#daoSummaryConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#daoSummaryConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-dao-summary/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#daoSummaryGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#daoSummaryGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#privilegeGrid tbody').on('click', 'button#viewPrivilege', function() {
	var data = $('#privilegeGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#privilegeViewTitle').html(data.name);
	$('#privilegeId').html(data.id);
    $('#privilegeName').html(data.name);
    $('#privilegeView').modal('show');
  });
  
  $('#privilegeGrid tbody').on('click', 'button#editPrivilege', function() {
	var data = $('#privilegeGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#privilegeEditTitle').html(data.name);
	$('#id').val(data.id);
    $('#name').val(data.name);
    $('#name').attr('value', data.name);
    $('#privilegeEdit').modal('show');
  });
  
  $('#privilegeGrid tbody').on('click', 'button#deletePrivilege', function() {
	var data = $('#privilegeGrid').DataTable().row($(this).parents('tr')).data();
	$('#privilegeConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#privilegeConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-privilege/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#privilegeGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#privilegeGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#roleGrid tbody').on('click', 'button#viewRole', function() {
	var data = $('#roleGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#roleViewTitle').html(data.name);
	$('#roleId').html(data.id);
    $('#roleName').html(data.name);
    $('#rolePrivileges').html(data.privileges);
    $('#roleUsers').html(data.users);
    $('#roleView').modal('show');
  });
  
  $('#roleGrid tbody').on('click', 'button#editRole', function() {
	var data = $('#roleGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#roleEditTitle').html(data.name);
	$('#id').val(data.id);
    $('#name').val(data.name);
    $('#name').attr('value', data.name);
    $.each(data.privileges.split(','), function(key, value) {
	  $('#privileges').val(value);
	  $('#privileges option[value="'+value+'"]').attr('selected', true);
	});
    $('#roleEdit').modal('show');
  });
  
  $('#roleEdit').on('hidden.bs.modal', function(e) {
	$('#privileges option').removeAttr('selected');
  });
  
  $('#roleGrid tbody').on('click', 'button#deleteRole', function() {
	var data = $('#roleGrid').DataTable().row($(this).parents('tr')).data();
	$('#roleConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#roleConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-role/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#roleGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#roleGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#userGrid tbody').on('click', 'button#viewUser', function() {
	var data = $('#userGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#userViewTitle').html(data.firstName + ' ' + data.lastName);
	$('#userId').html(data.id);
    $('#userUsername').html(data.username);
    $('#userFirstName').html(data.firstName);
    $('#userLastName').html(data.lastName);
    $('#userEmail').html(data.email);
    $('#userRole').html(data.role);
    $('#userEnabled').html(data.enabled);
    $('#userDao').html(data.dao);
    $('#userView').modal('show');
  });
  
  $('#userGrid tbody').on('click', 'button#editUser', function() {
	var data = $('#userGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#userEditTitle').html(data.firstName + ' ' + data.lastName);
	$('#id').val(data.id);
    $('#username').val(data.username);
    $('#username').attr('value', data.username);
    $('#firstName').val(data.firstName);
    $('#firstName').attr('value', data.firstName);
    $('#lastName').val(data.lastName);
    $('#lastName').attr('value', data.lastName);
    $('#email').val(data.email);
    $('#email').attr('value', data.email);
    $('#role').val(data.role);
    $('#role option[value="'+data.role+'"]').attr('selected', true);
    $('#dao').val(data.dao);
    $('#dao option[value="'+data.dao+'"]').attr('selected', true);

    if (data.enabled == 'Yes') {
      $('#enabled1').prop('checked', true);
      $('#enabled1').attr('checked', true);
    }
    if (data.enabled == 'No') {
      $('#enabled2').prop('checked', true);
      $('#enabled2').attr('checked', true);
    }
    
    $('#userEdit').modal('show');
  });
  
  $('#userGrid tbody').on('click', 'button#deleteUser', function() {
	var data = $('#userGrid').DataTable().row($(this).parents('tr')).data();
	$('#userConfirm').modal('show');
	
	$('button#yesConfirm').on('click', function() {
	  $('#userConfirm').modal('hide');
	  
	  $.ajax({
        url: './delete-user/' + data.id,
        type: 'delete',
        success: function(result) {
          $('#userGrid').DataTable().ajax.reload();
          $('#gridMessageSuccess').text(result);
    	  $('#gridSuccess').removeClass('d-none').addClass('d-block');
        },
	    error: function(e) {
          $('#userGrid').DataTable().ajax.reload();
          $('#gridMessageFailure').text(e.responseText);
    	  $('#gridFailure').removeClass('d-none').addClass('d-block');
        }
	  });
	});
  });
  
  $('#jobCheckGrid tbody').on('click', 'button#viewJobCheck', function() {
	var data = $('#jobCheckGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#jobCheckViewTitle').html(data.description);
	$('#jobCheckId').html(data.id);
    $('#jobCheckLastRun').html(data.lastRun);
    $('#jobCheckNextRun').html(data.nextRun);
    $('#jobCheckDescription').html(data.description);
    $('#jobCheckCode').html(data.code);
    $('#jobCheckView').modal('show');
  });
  
  $('#accrualReportGrid tbody').on('click', 'button#viewAccrualReport', function() {
	var data = $('#accrualReportGrid').DataTable().row($(this).parents('tr')).data();
	$('#accrualReportViewTitle').html(data.customerNo);
	
    $('#accrualDetailsReportGrid').DataTable({
      destroy: true,
      scrollX: true,
      ajax: {
	    url: './list-accrual-details-report-grid/' + data.id,
	    dataSrc: ''
	  },
	  columns: [
	    {data: 'id'},
	    {data: 'customerNo'},
	    {data: 'createdDate'},
	    {data: 'points'},
	    {data: 'pointsFlag'},
	    {data: 'fileId'},
	    {data: 'recordNo'},
	    {data: 'transRef'},
	    {data: 'amount'},
	    {data: 'narration1'},
	    {data: 'narration2'},
	    {data: 'description'},
	    {data: 'mobileNo'},
	    {data: 'transDate'},
	    {data: 'transCode'},
	    {data: 'dao'}
	  ],
	  columnDefs: [
        {
          targets: [0, 2, 5, 6, 9, 10, 11, 12, 13],
          visible: false
        }
      ],
      dom: "<'row'<'col-sm-12 col-md-12 col-lg-6'B><'col-sm-12 col-md-12 col-lg-6'f>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
	  buttons: [
		{
		  extend: 'copy',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		{
		  extend: 'csv',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		{
		  extend: 'excel',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		{
		  extend: 'pdf',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		{
		  extend: 'print',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		'colvis'
	  ]
    });
	
    $('#accrualReportView').modal('show');
  });
  
  $('#accrualReportView').on('shown.bs.modal', function(e) {
	$('#accrualDetailsReportGrid').DataTable().columns.adjust().draw();
  });
  
  $('#redemptionReportGrid tbody').on('click', 'button#viewRedemptionReport', function() {
	var data = $('#redemptionReportGrid').DataTable().row($(this).parents('tr')).data();
	$('#redemptionReportViewTitle').html(data.customerNo);
	
    $('#redemptionDetailsReportGrid').DataTable({
      destroy: true,
      scrollX: true,
      ajax: {
	    url: './list-redemption-details-report-grid/' + data.id,
	    dataSrc: ''
	  },
	  columns: [
	    {data: 'id'},
	    {data: 'customerNo'},
	    {data: 'createdDate'},
	    {data: 'points'},
	    {data: 'pointsFlag'},
	    {data: 'fileId'},
	    {data: 'recordNo'},
	    {data: 'transRef'},
	    {data: 'amount'},
	    {data: 'narration1'},
	    {data: 'narration2'},
	    {data: 'description'},
	    {data: 'mobileNo'},
	    {data: 'transDate'},
	    {data: 'transCode'},
	    {data: 'dao'}
	  ],
	  columnDefs: [
        {
          targets: [0, 2, 5, 6, 9, 10, 11, 12, 13],
          visible: false
        }
      ],
      dom: "<'row'<'col-sm-12 col-md-12 col-lg-6'B><'col-sm-12 col-md-12 col-lg-6'f>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
	  buttons: [
		{
		  extend: 'copy',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		{
		  extend: 'csv',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		{
		  extend: 'excel',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		{
		  extend: 'pdf',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		{
		  extend: 'print',
		  exportOptions: {
		    columns: ':visible'
		  }
		},
		'colvis'
	  ]
    });
	
    $('#redemptionReportView').modal('show');
  });
  
  $('#redemptionReportView').on('shown.bs.modal', function(e) {
	$('#redemptionDetailsReportGrid').DataTable().columns.adjust().draw();
  });
  
  $('#auditTrailGrid tbody').on('click', 'button#viewAuditTrail', function() {
	var data = $('#auditTrailGrid').DataTable().row($(this).parents('tr')).data();
	
	$('#auditTrailViewTitle').html(data.principal + " - " + data.remoteAddress);
	$('#auditTrailId').html(data.id);
    $('#auditTrailTimestamp').html(data.timestamp);
    $('#auditTrailPrincipal').html(data.principal);
    $('#auditTrailSessionId').html(data.sessionId);
    $('#auditTrailMethod').html(data.method);
    $('#auditTrailUri').html(data.uri);
    $('#auditTrailHost').html(data.host);
    $('#auditTrailReferrer').html(data.referrer);
    $('#auditTrailCookie').html(data.cookie);
    $('#auditTrailRemoteAddress').html(data.remoteAddress);
    $('#auditTrailStatus').html(data.status);
    $('#auditTrailTimeTaken').html(data.timeTaken);
    $('#auditTrailType').html(data.type);
    $('#auditTrailMessage').html(data.message);
    $('#auditTrailView').modal('show');
  });
});

function codeCategories(code, category) {
  $.get('./code-categories/' + code, function(data) {
    $('select#category').empty();
    $('select#category').append($('<option>', {
      value: '',
      text: 'Select category...',
      selected: true
    }));
    data.forEach(function(item) {
      if (item == category) {
    	$('select#category').append($('<option>', {
    	  value: item,
    	  text: item,
    	  selected: true
    	}));
      }
      else {
        $('select#category').append($('<option>', {
          value: item,
          text: item
        }));
      }
    });
  });
}

function codeNames(channelCode, productName) {
  $.get('./code-names/' + channelCode, function(data) {
    $('select#productName').empty();
    $('select#productName').append($('<option>', {
      value: '',
      text: 'Select product name...',
      selected: true
    }));
    data.forEach(function(item) {
      if (item == productName) {
    	$('select#productName').append($('<option>', {
    	  value: item,
    	  text: item,
    	  selected: true
    	}));
      }
      else {
        $('select#productName').append($('<option>', {
          value: item,
          text: item
        }));
      }
    });
  });
}