$(function() {
  $('#uploadCustomerGrid').DataTable({
	scrollX: true,
	serverSide: true,
	ajax: './list-upload-customer-grid',
	columns: [
	  {data: 'id'},
	  {data: 'name'},
	  {data: 'data'},
	  {data: 'uploadDate'},
	  {data: 'type'},
	  {data: 'status'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<a href="./download-customer-file/' + row.name + '" class="btn btn-sm btn-primary mr-2"><i class="fas fa-download"></i></a>' +
            '<button id="deleteCustomerFile" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 2],
        visible: false
      },
      {
        targets: [2],
        searchable: false
      },
      {
        targets: [6],
        orderable: false,
        searchable: false
      }
    ]
  });
  
  $('#uploadKcbGrid').DataTable({
	scrollX: true,
	serverSide: true,
	ajax: './list-upload-kcb-grid',
	columns: [
	  {data: 'id'},
	  {data: 'name'},
	  {data: 'data'},
	  {data: 'uploadDate'},
	  {data: 'type'},
	  {data: 'status'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<a href="./download-kcb-file/' + row.name + '" class="btn btn-sm btn-primary mr-2"><i class="fas fa-download"></i></a>' +
            '<button id="deleteKcbFile" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 2],
        visible: false
      },
      {
        targets: [2],
        searchable: false
      },
      {
        targets: [6],
        orderable: false,
        searchable: false
      }
    ]
  });
  
  $('#uploadInfiniaGrid').DataTable({
	scrollX: true,
	serverSide: true,
	ajax: './list-upload-infinia-grid',
	columns: [
	  {data: 'id'},
	  {data: 'name'},
	  {data: 'data'},
	  {data: 'uploadDate'},
	  {data: 'type'},
	  {data: 'status'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<a href="./download-infinia-file/' + row.name + '" class="btn btn-sm btn-primary mr-2"><i class="fas fa-download"></i></a>' +
            '<button id="deleteInfiniaFile" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 2],
        visible: false
      },
      {
        targets: [2],
        searchable: false
      },
      {
        targets: [6],
        orderable: false,
        searchable: false
      }
    ]
  });
  
  $('#recordsCustomerGrid').DataTable({
	scrollX: true,
	serverSide: true,
	ajax: './list-records-customer-grid',
	columns: [
	  {data: 'id'},
	  {data: 'customerCode'},
	  {data: 'mnemonic'},
	  {data: 'shortName'},
	  {data: 'name'},
	  {data: 'optInDate'},
	  {data: 'street'},
	  {data: 'address'},
	  {data: 'town'},
	  {data: 'postCode'},
	  {data: 'country'},
	  {data: 'sector'},
	  {data: 'accountOfficer'},
	  {data: 'otherOfficer'},
	  {data: 'industry'},
	  {data: 'target'},
	  {data: 'nationality'},
	  {data: 'customerStatus'},
	  {data: 'residence'},
	  {data: 'contactDate'},
	  {data: 'custOffice'},
	  {data: 'custMobile'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewCustomer" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editCustomer" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteCustomer" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      }
	],
	columnDefs: [
      {
        targets: [0, 3, 5, 6, 7, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20],
        visible: false
      },
      {
        targets: [22],
        orderable: false,
        searchable: false
      }
    ]
  });
  
  $('#recordsKcbGrid').DataTable({
	scrollX: true,
	serverSide: true,
    ajax: './list-records-kcb-grid',
	columns: [
	  {data: 'id'},
	  {data: 'fileId'},
	  {data: 'recordNo'},
	  {data: 'customerNo'},
	  {data: 'mainTransCode'},
	  {data: 'valueX1'},
	  {data: 'accountType'},
	  {data: 'transCode'},
	  {data: 'incomingDate'},
	  {data: 'incomingTime'},
	  {data: 'transRef'},
	  {data: 'accountNo'},
	  {data: 'narration'},
	  {data: 'depositDao'},
	  {data: 'valueX2'},
	  {data: 'valueX3'},
	  {data: 'valueX4'},
	  {data: 'drValue'},
	  {data: 'valueX5'},
	  {data: 'amount1'},
	  {data: 'amount2'},
	  {data: 'currency'},
	  {data: 'matched'},
	  {data: 'checkFlag'},
	  {data: 'pointsEarned'},
	  {data: 'closed'},
	  {data: 'successErrorFlag'},
	  {data: 'generated'},
	  {data: 'processed'},
	  {data: 'returned'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewKcb" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editKcb" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteKcb" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      }
	],
	columnDefs: [
      {
        targets: [0, 2, 4, 5, 6, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 21, 22, 23, 25, 27, 28, 29],
        visible: false
      },
      {
        targets: [30],
        orderable: false,
        searchable: false
      }
    ]
  });
  
  $('#recordsInfiniaGrid').DataTable({
	scrollX: true,
	serverSide: true,
    ajax: './list-records-infinia-grid',
	columns: [
	  {data: 'id'},
	  {data: 'fileId'},
	  {data: 'recordNo'},
	  {data: 'customerNo'},
	  {data: 'mainTransCode'},
	  {data: 'valueX1'},
	  {data: 'accountType'},
	  {data: 'transCode'},
	  {data: 'incomingDate'},
	  {data: 'incomingTime'},
	  {data: 'transRef'},
	  {data: 'accountNo'},
	  {data: 'narration'},
	  {data: 'depositDao'},
	  {data: 'valueX2'},
	  {data: 'valueX3'},
	  {data: 'valueX4'},
	  {data: 'drValue'},
	  {data: 'valueX5'},
	  {data: 'amount1'},
	  {data: 'amount2'},
	  {data: 'currency'},
	  {data: 'valueX6'},
	  {data: 'valueX7'},
	  {data: 'pointsEarned'},
	  {data: 'successErrorFlag'},
	  {data: 'matched'},
	  {data: 'checkFlag'},
	  {data: 'closed'},
	  {data: 'errorCode'},
	  {data: 'generated'},
	  {data: 'processed'},
	  {data: 'returned'},
	  {
		data: null,
        render: function(data, type, row, meta) {
        	return '<button id="viewInfinia" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
              '<button id="editInfinia" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
              '<button id="deleteInfinia" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      }
	],
	columnDefs: [
	  {
	    targets: [0, 1, 2, 4, 5, 7, 8, 9, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32],
	    visible: false
	  },
	  {
	    targets: [33],
	    orderable: false,
	    searchable: false
	  }
    ]
  });
  
  $('#currencyGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-currency-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'codeNumber'},
	  {data: 'abbreviation'},
	  {data: 'name'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewCurrency" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editCurrency" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteCurrency" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0],
        visible: false
      },
      {
    	targets: [4],
    	orderable: false
      }
    ]
  });
  
  $('#productCodeGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-product-code-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'code'},
	  {data: 'description'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewProductCode" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editProductCode" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteProductCode" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0],
        visible: false
      },
      {
    	targets: [3],
    	orderable: false
      }
    ]
  });
  
  $('#channelGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-channel-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'code'},
	  {data: 'category'},
	  {data: 'subCategory'},
	  {data: 'dealFlag'},
	  {data: 'active'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewChannel" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editChannel" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteChannel" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 4, 5],
        visible: false
      },
      {
    	targets: [6],
    	orderable: false
      }
    ]
  });
  
  $('#pointsMatrixGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-points-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'channelName'},
	  {data: 'channelCode'},
	  {data: 'productName'},
	  {data: 'productCode'},
	  {data: 'dealCode'},
	  {data: 'pos'},
	  {data: 'minDay'},
	  {data: 'maxDay'},
	  {data: 'pointsValue'},
	  {data: 'pointsAmount'},
	  {data: 'onlineTransCode'},
	  {data: 'intlTransCode'},
	  {data: 'posTxnCode'},
	  {data: 'currency'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewPoints" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editPoints" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deletePoints" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 5, 6, 7, 8, 11, 12, 13, 14],
        visible: false
      },
      {
    	targets: [15],
    	orderable: false
      }
    ]
  });
  
  $('#daoGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-dao-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'branchCode'},
	  {data: 'branchName'},
	  {data: 'profitLossAc'},
	  {data: 'loyaltyAc'},
	  {data: 'holdingAc'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewDao" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editDao" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteDao" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 3, 5],
        visible: false
      },
      {
    	targets: [6],
    	orderable: false
      }
    ]
  });
  
  $('#transactionCodeGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-transaction-code-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'code'},
	  {data: 'description'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewTransactionCode" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editTransactionCode" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteTransactionCode" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0],
        visible: false
      },
      {
    	targets: [3],
    	orderable: false
      }
    ]
  });
  
  $('#reasonCodeGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-reason-code-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'code'},
	  {data: 'description'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewReasonCode" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editReasonCode" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteReasonCode" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0],
        visible: false
      },
      {
    	targets: [3],
    	orderable: false
      }
    ]
  });
  
  $('#directoryGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-directory-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'kcbInDir'},
	  {data: 'kcbOutDir'},
	  {data: 'infiniaInDir'},
	  {data: 'infiniaOutDir'},
	  {data: 'customerInDir'},
	  {data: 'customerOutDir'},
	  {data: 'kcbBkDir'},
	  {data: 'infiniaBkDir'},
	  {data: 'customerBkDir'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewDirectory" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editDirectory" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 2, 4, 6, 7, 8, 9],
        visible: false
      },
      {
    	targets: [10],
    	orderable: false
      }
    ]
  });
  
  $('#merchantGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-merchant-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'mId'},
	  {data: 'code'},
	  {data: 'name'},
	  {data: 'password'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewMerchant" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editMerchant" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteMerchant" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 2, 4],
        visible: false
      },
      {
    	targets: [5],
    	orderable: false
      }
    ]
  });
  
  $('#transSummaryGrid').DataTable({
	scrollX: true,
    ajax: {
	  url: './list-trans-summary-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'infiniaFileId'},
	  {data: 'kcbFileId'},
	  {data: 'infiniaFileCount'},
	  {data: 'kcbFileCount'},
	  {data: 'countMatched'},
	  {data: 'allCustomersExist'},
	  {data: 'nonCustomerCountInf'},
	  {data: 'nonCustomerCountKcb'},
	  {data: 'infiniaTotalPoints'},
	  {data: 'kcbTotalPoints'},
	  {data: 'pointsMatched'},
	  {data: 'infiniaDistinctTxnCode'},
	  {data: 'kcbDistinctTxnCode'},
	  {data: 'distinctTxnCodeMatched'},
	  {data: 'infiniaSuccessCount'},
	  {data: 'kcbSuccessCount'},
	  {data: 'successCountMatched'},
	  {data: 'infiniaErrorCount'},
	  {data: 'kcbErrorCount'},
	  {data: 'errorCountMatched'},
	  {data: 'infiniaTotalAmount1'},
	  {data: 'kcbTotalAmount1'},
	  {data: 'amount1Matched'},
	  {data: 'infiniaTotalAmount2'},
	  {data: 'kcbTotalAmount2'},
	  {data: 'amount2Matched'},
	  {data: 'fileMatched'},
	  {data: 'closed'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewTransSummary" class="btn btn-sm btn-success"><i class="fas fa-eye"></i></button>';
        }
      }
	],
	columnDefs: [
      {
        targets: [0, 1, 3, 4, 6, 7, 8, 9, 10, 12, 13, 15, 16, 18, 19, 21, 22, 24, 25],
        visible: false
      },
      {
        targets: [29],
        orderable: false
      }
    ]
  });
  
  $('#redemptionSummaryGrid').DataTable({
	scrollX: true,
	serverSide: true,
    ajax: './list-redemption-summary-grid',
	columns: [
	  {data: 'id'},
	  {data: 'customerNo'},
	  {data: 'name'},
	  {data: 'custMobile'},
	  {data: 'dao'},
	  {data: 'pointsEarned'},
	  {data: 'pointsRedeemed'},
	  {data: 'pointsBalance'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewRedemption" class="btn btn-sm btn-success"><i class="fas fa-eye"></i></button>';
        }
      }
	],
	columnDefs: [
      {
        targets: [0, 3, 4],
        visible: false
      },
      {
        targets: [8],
        orderable: false,
        searchable: false
      }
    ]
  });
  
  $('#daoSummaryGrid').DataTable({
	scrollX: true,
    ajax: {
	  url: './list-dao-summary-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'dao'},
	  {data: 'amount1'},
	  {data: 'amount2'},
	  {data: 'totalPoints'},
	  {data: 'posting75'},
	  {data: 'posting25'},
	  {data: 'profitLossAc'},
	  {data: 'suspenseAc'},
	  {data: 'holdingAc'},
	  {data: 'postingDate'},
	  {data: 't24Ref'},
	  {data: 'messageId'},
	  {data: 'fileId'},
	  {data: 'description'},
	  {data: 'reason'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewDaoSummary" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editDaoSummary" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteDaoSummary" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      }
	],
	columnDefs: [
      {
        targets: [0, 2, 5, 6, 7, 8, 9, 10, 12, 14, 15],
        visible: false
      },
      {
        targets: [16],
        orderable: false
      }
    ]
  });
  
  $('#privilegeGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-privilege-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'name'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewPrivilege" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editPrivilege" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deletePrivilege" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0],
        visible: false
      },
      {
    	targets: [2],
    	orderable: false
      }
    ]
  });
  
  $('#roleGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-role-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'name'},
	  {data: 'privileges'},
	  {data: 'users'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewRole" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editRole" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteRole" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0],
        visible: false
      },
      {
    	targets: [4],
    	orderable: false
      },
      {
    	targets: [2],
    	createdCell: function(td, cellData, rowData, row, col) {
          $(td).attr('id', 'gridPrivileges'); 
        }
      },
      {
      	targets: [3],
      	createdCell: function(td, cellData, rowData, row, col) {
          $(td).attr('id', 'gridUsers'); 
        }
      }
    ]
  });
  
  $('#userGrid').DataTable({
	scrollX: true,
	ajax: {
	  url: './list-user-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'username'},
	  {data: 'firstName'},
	  {data: 'lastName'},
	  {data: 'email'},
	  {data: 'role'},
	  {data: 'enabled'},
	  {data: 'dao'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewUser" class="btn btn-sm btn-success mr-2"><i class="fas fa-eye"></i></button>' +
            '<button id="editUser" class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i></button>' +
            '<button id="deleteUser" class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></button>';
        }
      },
	],
	columnDefs: [
      {
        targets: [0, 2, 3, 6],
        visible: false
      },
      {
    	targets: [8],
    	orderable: false
      }
    ]
  });
  
  $('#jobCheckGrid').DataTable({
	scrollX: true,
    ajax: {
	  url: './list-job-check-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'lastRun'},
	  {data: 'nextRun'},
	  {data: 'description'},
	  {data: 'code'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewJobCheck" class="btn btn-sm btn-success"><i class="fas fa-eye"></i></button>';
        }
      }
	],
	columnDefs: [
      {
        targets: [0],
        visible: false
      },
      {
        targets: [5],
        orderable: false
      }
    ]
  });
  
  $('#accrualReportGrid').DataTable({
	scrollX: true,
	serverSide: true,
	ajax: './list-redemption-summary-report-grid',
	columns: [
	  {data: 'id'},
	  {data: 'customerNo'},
	  {data: 'name'},
	  {data: 'custMobile'},
	  {data: 'dao'},
	  {data: 'pointsEarned'},
	  {data: 'pointsRedeemed'},
	  {data: 'pointsBalance'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewAccrualReport" class="btn btn-sm btn-success"><i class="fas fa-eye"></i></button>';
        }
      }
	],
	columnDefs: [
	  {
	    targets: [0, 3, 4, 6, 7],
	    visible: false
	  },
      {
        targets: [8],
        orderable: false,
        searchable: false
      }
    ],
    dom: "<'row'<'col-sm-12 col-md-6'B><'col-sm-12 col-md-6'f>>" +
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
  
  $('#redemptionReportGrid').DataTable({
	scrollX: true,
	serverSide: true,
	ajax: './list-redemption-summary-report-grid',
	columns: [
	  {data: 'id'},
	  {data: 'customerNo'},
	  {data: 'name'},
	  {data: 'custMobile'},
	  {data: 'dao'},
	  {data: 'pointsEarned'},
	  {data: 'pointsRedeemed'},
	  {data: 'pointsBalance'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewRedemptionReport" class="btn btn-sm btn-success"><i class="fas fa-eye"></i></button>';
        }
      }
	],
	columnDefs: [
      {
        targets: [0, 3, 4, 5, 7],
        visible: false
      },
      {
        targets: [8],
        orderable: false,
        searchable: false
      }
    ],
    dom: "<'row'<'col-sm-12 col-md-6'B><'col-sm-12 col-md-6'f>>" +
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
  
  $('#memberReportGrid').DataTable({
	scrollX: true,
	serverSide: true,
	ajax: './list-member-report-grid',
	columns: [
	  {data: 'id'},
	  {data: 'customerCode'},
	  {data: 'mnemonic'},
	  {data: 'shortName'},
	  {data: 'name'},
	  {data: 'optInDate'},
	  {data: 'street'},
	  {data: 'address'},
	  {data: 'town'},
	  {data: 'postCode'},
	  {data: 'country'},
	  {data: 'sector'},
	  {data: 'accountOfficer'},
	  {data: 'otherOfficer'},
	  {data: 'industry'},
	  {data: 'target'},
	  {data: 'nationality'},
	  {data: 'customerStatus'},
	  {data: 'residence'},
	  {data: 'contactDate'},
	  {data: 'custOffice'},
	  {data: 'custMobile'}
	],
	columnDefs: [
      {
        targets: [0, 3, 5, 6, 7, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20],
        visible: false
      }
    ],
    dom: "<'row'<'col-sm-12 col-md-6'B><'col-sm-12 col-md-6'f>>" +
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
  
  $('#pointsBalanceReportGrid').DataTable({
	scrollX: true,
	serverSide: true,
	ajax: './list-redemption-summary-report-grid',
	columns: [
	  {data: 'id'},
	  {data: 'customerNo'},
	  {data: 'name'},
	  {data: 'custMobile'},
	  {data: 'dao'},
	  {data: 'pointsEarned'},
	  {data: 'pointsRedeemed'},
	  {data: 'pointsBalance'}
	],
	columnDefs: [
      {
        targets: [0, 3, 4, 5, 6],
        visible: false
      }
    ],
    dom: "<'row'<'col-sm-12 col-md-6'B><'col-sm-12 col-md-6'f>>" +
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
  
  $('#processingReportGrid').DataTable({
	scrollX: true,
	serverSide: true,
    ajax: './list-processing-report-grid',
	columns: [
	  {data: 'id'},
	  {data: 'fileId'},
	  {data: 'recordNo'},
	  {data: 'customerNo'},
	  {data: 'mainTransCode'},
	  {data: 'valueX1'},
	  {data: 'accountType'},
	  {data: 'transCode'},
	  {data: 'incomingDate'},
	  {data: 'incomingTime'},
	  {data: 'transRef'},
	  {data: 'accountNo'},
	  {data: 'narration'},
	  {data: 'depositDao'},
	  {data: 'valueX2'},
	  {data: 'valueX3'},
	  {data: 'valueX4'},
	  {data: 'drValue'},
	  {data: 'valueX5'},
	  {data: 'amount1'},
	  {data: 'amount2'},
	  {data: 'currency'},
	  {data: 'matched'},
	  {data: 'checkFlag'},
	  {data: 'pointsEarned'},
	  {data: 'closed'},
	  {data: 'successErrorFlag'},
	  {data: 'generated'},
	  {data: 'processed'},
	  {data: 'returned'}
	],
	columnDefs: [
      {
        targets: [0, 2, 4, 5, 6, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 21, 22, 23, 25, 27, 28, 29],
        visible: false
      }
    ],
    dom: "<'row'<'col-sm-12 col-md-6'B><'col-sm-12 col-md-6'f>>" +
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
  
  $('#transactionReportGrid').DataTable({
	scrollX: true,
    ajax: {
	  url: './list-transaction-report-grid',
	  dataSrc: ''
	},
	columns: [
	  {data: 'id'},
	  {data: 'infiniaFileId'},
	  {data: 'kcbFileId'},
	  {data: 'infiniaFileCount'},
	  {data: 'kcbFileCount'},
	  {data: 'countMatched'},
	  {data: 'allCustomersExist'},
	  {data: 'nonCustomerCountInf'},
	  {data: 'nonCustomerCountKcb'},
	  {data: 'infiniaTotalPoints'},
	  {data: 'kcbTotalPoints'},
	  {data: 'pointsMatched'},
	  {data: 'infiniaDistinctTxnCode'},
	  {data: 'kcbDistinctTxnCode'},
	  {data: 'distinctTxnCodeMatched'},
	  {data: 'infiniaSuccessCount'},
	  {data: 'kcbSuccessCount'},
	  {data: 'successCountMatched'},
	  {data: 'infiniaErrorCount'},
	  {data: 'kcbErrorCount'},
	  {data: 'errorCountMatched'},
	  {data: 'infiniaTotalAmount1'},
	  {data: 'kcbTotalAmount1'},
	  {data: 'amount1Matched'},
	  {data: 'infiniaTotalAmount2'},
	  {data: 'kcbTotalAmount2'},
	  {data: 'amount2Matched'},
	  {data: 'fileMatched'},
	  {data: 'closed'}
	],
	columnDefs: [
      {
        targets: [0, 1, 3, 4, 6, 7, 8, 9, 10, 12, 13, 15, 16, 18, 19, 21, 22, 24, 25],
        visible: false
      }
    ],
    dom: "<'row'<'col-sm-12 col-md-6'B><'col-sm-12 col-md-6'f>>" +
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
  
  $('#auditTrailGrid').DataTable({
	scrollX: true,
	serverSide: true,
    ajax: './list-audit-trail-grid',
	columns: [
	  {data: 'id'},
	  {data: 'timestamp'},
	  {data: 'principal'},
	  {data: 'sessionId'},
	  {data: 'method'},
	  {data: 'uri'},
	  {data: 'host'},
	  {data: 'referrer'},
	  {data: 'cookie'},
	  {data: 'remoteAddress'},
	  {data: 'status'},
	  {data: 'timeTaken'},
	  {data: 'type'},
	  {data: 'message'},
	  {
		data: null,
        render: function(data, type, row, meta) {
          return '<button id="viewAuditTrail" class="btn btn-sm btn-success"><i class="fas fa-eye"></i></button>';
        }
      }
	],
	columnDefs: [
      {
        targets: [0, 3, 6, 7, 8, 9, 10, 11, 12, 13],
        visible: false
      },
      {
        targets: [14],
        orderable: false,
        searchable: false
      }
    ],
    order: [[1, 'desc']]
  });
});