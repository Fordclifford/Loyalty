$(function() {
  var token = $('meta[name="_csrf"]').attr('content');
  var header = $('meta[name="_csrf_header"]').attr('content');
  
  $(this).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader(header, token);
  });
  
  $(this).ajaxStart(function() {
	$('.loader').removeClass('d-none').addClass('d-block');
  }).ajaxStop(function() {
	$('.loader').removeClass('d-block').addClass('d-none');
  });
  
  $(this).on('click', 'a[data-toggle="collapse"]', function() {
	var value = $(this).attr('aria-expanded');
	
    if (value == 'true') {
	  $(this).children().removeClass('fa-angle-right').addClass('fa-angle-down');
    }
    else {
      $(this).children().removeClass('fa-angle-down').addClass('fa-angle-right');
    }
    
    $('a.list-group-item-action.collapsed').children().removeClass('fa-angle-down').addClass('fa-angle-right');
  });
  
  $('div.list-group > a.list-group-item.active').parent().addClass('show');
  $('div.list-group > div.list-group > a.list-group-item.active').parent().parent().addClass('show');
  
  $('a.list-group-item-action.active').parent().prev().attr('aria-expanded', 'true');
  $('a.list-group-item-action.active').parent().parent().prev().attr('aria-expanded', 'true');
  $('a.list-group-item-action.active').parent().prev().children().removeClass('fa-angle-right').addClass('fa-angle-down');
  $('a.list-group-item-action.active').parent().parent().prev().children().removeClass('fa-angle-right').addClass('fa-angle-down');
  
  $('select#code').change(function() {
    loadCodeCategories();
  });
  
  $('select#channelCode').change(function() {
    loadCodeNames();
  });
});

function loadCodeCategories() {
  var code = $('select#code').val();
  $.get('./code-categories/' + code, function(data) {
    $('select#category').empty();
    $('select#category').append($('<option>', {
      value: '',
      text: 'Select category...',
      selected: true
    }));
    data.forEach(function(item) {
      $('select#category').append($('<option>', {
        value: item,
        text: item
      }));
    });
  });
}

function loadCodeNames() {
  var channelCode = $('select#channelCode').val();
  $.get('./code-names/' + channelCode, function(data) {
    $('select#productName').empty();
    $('select#productName').append($('<option>', {
      value: '',
      text: 'Select product name...',
      selected: true
    }));
    data.forEach(function(item) {
      $('select#productName').append($('<option>', {
        value: item,
        text: item
      }));
    });
  });
}