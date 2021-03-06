/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Assignment
 */
var KisBpmAssignmentCtrl = [ '$scope', '$modal', function($scope, $modal) {

	// Config for the modal window
	var opts = {
		template:  'editor-app/configuration/properties/assignment-popup.html?version=' + Date.now(),
		scope: $scope
	};

	// Open the dialog
	$modal(opts);


}];

var KisBpmAssignmentPopupCtrl = [ '$scope', '$http', function($scope, $http) {

	// Put json representing assignment on scope
	if ($scope.property.value !== undefined && $scope.property.value !== null
		&& $scope.property.value.assignment !== undefined
		&& $scope.property.value.assignment !== null)
	{
		$scope.assignment = $scope.property.value.assignment;
	} else {
		$scope.assignment = {};
	}

	if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0)
	{
		$scope.assignment.candidateUsers = [{value: ''}];
	}

	// Click handler for + button after enum value
	var userValueIndex = 1;
	$scope.addCandidateUserValue = function(index) {
		$scope.assignment.candidateUsers.splice(index + 1, 0, {value: 'value ' + userValueIndex++});
	};

	// Click handler for - button after enum value
	$scope.removeCandidateUserValue = function(index) {
		$scope.assignment.candidateUsers.splice(index, 1);
	};

	if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0)
	{
		$scope.assignment.candidateGroups = [{value: ''}];
	}

	var groupValueIndex = 1;
	$scope.addCandidateGroupValue = function(index) {
		$scope.assignment.candidateGroups.splice(index + 1, 0, {value: 'value ' + groupValueIndex++});
	};

	// Click handler for - button after enum value
	$scope.removeCandidateGroupValue = function(index) {
		$scope.assignment.candidateGroups.splice(index, 1);
	};

	$scope.save = function() {

		$scope.property.value = {};
		handleAssignmentInput($scope);
		$scope.property.value.assignment = $scope.assignment;

		$scope.updatePropertyInModel($scope.property);
		$scope.close();
	};

	// Close button handler
	$scope.close = function() {
		handleAssignmentInput($scope);
		$scope.property.mode = 'read';
		$scope.$hide();
	};

	var handleAssignmentInput = function($scope) {
		if ($scope.assignment.candidateUsers)
		{
			var emptyUsers = true;
			var toRemoveIndexes = [];
			for (var i = 0; i < $scope.assignment.candidateUsers.length; i++)
			{
				if ($scope.assignment.candidateUsers[i].value != '')
				{
					emptyUsers = false;
				}
				else
				{
					toRemoveIndexes[toRemoveIndexes.length] = i;
				}
			}

			for (var i = 0; i < toRemoveIndexes.length; i++)
			{
				$scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
			}

			if (emptyUsers)
			{
				$scope.assignment.candidateUsers = undefined;
			}
		}

		if ($scope.assignment.candidateGroups)
		{
			var emptyGroups = true;
			var toRemoveIndexes = [];
			for (var i = 0; i < $scope.assignment.candidateGroups.length; i++)
			{
				if ($scope.assignment.candidateGroups[i].value != '')
				{
					emptyGroups = false;
				}
				else
				{
					toRemoveIndexes[toRemoveIndexes.length] = i;
				}
			}

			for (var i = 0; i < toRemoveIndexes.length; i++)
			{
				$scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
			}

			if (emptyGroups)
			{
				$scope.assignment.candidateGroups = undefined;
			}
		}
	};

	// $scope.selectChange = function () {
	// 	var myselect=document.getElementById("selectedType");
	// 	var index=myselect.selectedIndex ;
	// 	var val = myselect.options[index].value;
	// 	alert(val);
	// }


	$scope.typeChange = function(){

		$scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	}


	/*---------------------???????????????????????????????????????--------------------*/
//???????????????
	$scope.gridData = [];//????????????
	$scope.gridDataName = 'gridData';
	$scope.selectTitle = '???????????????';
	$scope.columnData = [];//???????????????
	$scope.columnDataName = 'columnData';
	$scope.selectType = 0;//1.????????? 2.????????? 3.?????????
	$scope.totalServerItems = 0;//???????????????
	//???????????????
	$scope.pagingOptions = {
		pageSizes: [10, 20, 30],//page ???????????????
		pageSize: 10, //????????????
		currentPage: 1, //??????????????????
	};
	//????????????
	$scope.dataWatch = function (){
		//??????????????????
		$scope.$watch('pagingOptions', function (newValue, oldValue) {
			$scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
		},true);

		//???????????????????????????????????????
		$scope.$watch('selectType', function (newValue, oldValue) {
			if(newValue != oldValue){
				$scope.pagingOptions.currentPage = 1;
				$scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
			}
		},true);

		//????????????
		$scope.change = function(x){
			$scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
		};
	};

	$scope.dataWatch();
	//????????????????????????
	$scope.getPagedDataAsync = function(pageNum, pageSize){
		var myselect=document.getElementById("selectedType");
		var index=myselect.selectedIndex ;
		var val = myselect.options[index].value;
		console.log(val);
		var url;
		// if($scope.selectType == 2){
		// 	url = '/user/queryAssignee';
		// 	$scope.columnData = $scope.groupColumns;
		// }else{
		// 	url = '/user/queryAssignee';
		// 	$scope.columnData = $scope.userColumns;
		// }
		if("user" == val){
			url = '/assignment/user/list';
			$scope.columnData = $scope.userColumns;
		}else if ("role" == val){
			url = '/assignment/role/list';
			$scope.columnData = $scope.userColumns;
		}else if ("department" == val){
			url = '/assignment/department/list';
			$scope.columnData = $scope.userColumns;
		}else if ("depend" == val){
			url = "/assignment/common/list"
		}
		$http({
			method: 'POST',
			// url: ACTIVITI.CONFIG.contextRoot+url,
			url: url,
			params:{
				'pageNum': pageNum,
				'pageSize': pageSize
			},
		}).then(function successCallback(response) {
			$scope.gridData = response.data.list;
			$scope.totalServerItems = response.data.total;
		}, function errorCallback(response) {
			// ????????????????????????
			$scope.gridData = [];
			$scope.totalServerItems = 0;
		});
	}

	//??????????????????
	$scope.gridOptions = {
		data: $scope.gridDataName,
		multiSelect: false,//????????????
		enablePaging: true,
		pagingOptions: $scope.pagingOptions,
		totalServerItems: 'totalServerItems',
		i18n:'zh-cn',
		showFooter: true,
		showSelectionCheckbox: false,
		columnDefs : $scope.columnDataName,

		beforeSelectionChange: function (event) {
			// var data = event.entity.id;
			var Mydata = event.entity.name;
			if($scope.selectType == 0){//????????????
			event.entity.checked = !event.selected;
			$scope.assignment.assignee = Mydata;
			}
			// else if($scope.selectType == 1){//?????????
			// 	var obj = {value: data};
			// 	if(array_contain($scope.assignment.candidateUsers,'')){
			// 		$scope.assignment.candidateUsers=[obj];
			// 	}else{
			// 		if(!array_contain($scope.assignment.candidateUsers, obj.value)){
			// 			$scope.assignment.candidateUsers.push(obj);
			// 		}
			// 	}
			// }else if($scope.selectType == 2){//?????????
			// 	var obj = {value: $scope.getGroupData(event.entity)};
			// 	if(array_contain($scope.assignment.candidateGroups,'')){
			// 		$scope.assignment.candidateGroups=[obj];
			// 	}else{
			// 		if(!array_contain($scope.assignment.candidateGroups, obj.value)){
			// 			$scope.assignment.candidateGroups.push(obj);
			// 		}
			// 	}
			// }
			return true;
		}
	};

	$scope.getGroupData = function(data){
		return data.id;
	};

	//?????????????????????
	$scope.userColumns = [
		{
			field : 'id',
			displayName : '??????Id',
			minWidth: 100,
			width : '18%'
		},
		{
			field : 'first',
			displayName : '??????',
			minWidth: 100,
			width : '25%'
		},
		// {
		// 	field : 'email',
		// 	displayName : '??????',
		// 	minWidth: 100,
		// 	width : '25%'
		// }
	];
	//????????????????????????
	$scope.groupColumns = [
		{
			field : 'id',
			displayName : 'ID',
			minWidth: 100,
			width : '16%'
		},
		{
			field : 'name',
			displayName : '????????????',
			minWidth: 100,
			width : '25%'
		}
	];

	//?????????(?????????)
	$scope.selectAssignee = function () {
		$scope.selectType = 0;
		$scope.selectTitle = '???????????????';
	};

	//?????????
	$scope.selectCandidate = function () {
		$scope.selectType = 1;
		$scope.selectTitle = '???????????????';
	};

	//?????????
	$scope.selectGroup = function () {
		$scope.selectType = 2;
		$scope.selectTitle = '???????????????';
	};

}];

//??????----???????????? contains ???????????????
function array_contain(array, obj){
	for (var i = 0; i < array.length; i++){
		if (array[i].value == obj)//????????????????????????????????????????????????????????????===
			return true;
	}
	return false;
}