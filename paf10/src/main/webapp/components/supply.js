$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateSupplyForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidSupIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "SupplyAPI",
 type : type,
 data : $("#formsupply").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 });
});
function onItemSaveComplete(response, status)
{
	//Your code
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else	
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	
	$("#hidSupIDSave").val("");
	$("#formSupply")[0].reset();
}



$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidSupIDSave").val($(this).closest("tr").find('#hidSupIDUpdate').val());
	$("#supplyid").val($(this).closest("tr").find('td:eq(0)').text());
	$("#supplyName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#supplyquntity").val($(this).closest("tr").find('td:eq(2)').text());
	$("#supplyprice").val($(this).closest("tr").find('td:eq(3)').text());
	$("#total").val($(this).closest("tr").find('td:eq(4)').text());
	$("#date").val($(this).closest("tr").find('td:eq(5)').text());

});


$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "SupplyAPI",
type : "DELETE",
data : "SupID=" + $(this).data("supid"),
dataType : "text",
complete : function(response, status)
{
onItemDeleteComplete(response.responseText, status);
}
});
});



function onItemDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}

// CLIENT-MODEL==================================================================================
function validateItemForm() {
	// CODE
	if ($("#supplyid").val().trim() == "") {
		return "Insert supplyid.";
	}
	// NAME
	if ($("#supplyName").val().trim() == "") {
		return "Insert supplyName!";
	}
	if ($("#supplyquntity").val().trim() == "") {
		return "Insert supplyquntity!";
    }
	// PRICE-------------------------------
	if ($("#supplyprice").val().trim() == "") {
		return "Insert supplyprice !";
	}
	// is numerical value
	var tmpPrice = $("#supplyprice").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for supplyprice !";
	}
	// convert to decimal price
	$("#supplyprice").val(parseFloat(tmpPrice).toFixed(2));
	// DESCRIPTION------------------------
	if ($("#total").val().trim() == "") {
		return "Insert supply quntity !";
		
	}
	if ($("#date").val().trim() == "") {
		return "Insert supply date !";
	}
	return true;
}
