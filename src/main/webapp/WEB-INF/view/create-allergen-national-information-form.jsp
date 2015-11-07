<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet"
          href="<c:url value="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />">
    <style type="text/css">
        .btn {
            width: 200px;
        }

        form {
            margin-top: 20px;
        }

    </style>

</head>

<body>


<div class="container">
    <h1 class="page-header">Allergen Information Admin Console</h1>


    <form role="form" id="allergenAdviceForm">
        <div id="status" class="alert" style="display:none"></div>
        <fieldset>
            <legend>Update Allergen Information Per Country</legend>
            <div class="form-group">
                <div class="form-group">
                    <label for="countryName" class="col-lg-2 control-label">Country Name</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="countryName" placeholder="Country Name"
                               value="${country.countryName}">
                    </div>
                    <label for="countryISO" class="col-lg-2 control-label">Country ISO</label>

                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="countryISO" placeholder="Country ISO"
                               value="${country.countryISO}">
                        <span class="help-block">If unsure about the official ISO of a country check <a
                                href="https://www.iso.org/obp/ui/#search">here</a>.</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="allergenFoodGroups" class="col-lg-2 control-label">Food groups that must be displayed in
                        package
                        contents</label>

                    <div class="col-lg-10">
                        <textarea class="form-control" rows="3" id="allergenFoodGroups"><c:forEach
                                items="${country.listedHighRiskFoods}" var="food"><c:out
                                value="${food}"/>&#13;</c:forEach>
                        </textarea>
                        <span class="help-block">Separate each food group by storing it on a new line.</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="specialInfo" class="col-lg-2 control-label">Country Specific Allergen Food Laws</label>

                    <div class="col-lg-10">
                        <textarea class="form-control" rows="3" id="specialInfo"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="nationalWebsite" class="col-lg-2 control-label">National Website Relating To Food
                        Laws</label>

                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="nationalWebsite" placeholder="National Food Laws Website (If Applicable)"
                               value="${country.linkToNationalAllergenHealthWebsite}">
                    </div>
                </div>
            </div>

        </fieldset>

        <input type="submit" class="btn btn-primary pull-left" name="saveMessagesSubmit" id="saveMessagesSubmit"
               value="Save"/>
        <button class="btn btn-danger pull-right" name="deleteMessagesSubmit" id="deleteMessagesSubmit">Delete</button>
    </form>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate-1.12.0.min.js" />"></script>

<script type="text/javascript">
    var resourceUrl = '<c:url value="/allergen_advice" />';

    $.validator.setDefaults({
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block'
    });

    $(document).ready(function () {
        $("#allergenAdviceForm").validate();
        var $status = $('#status');

        $('#allergenAdviceForm').submit(function (ev) {
            ev.preventDefault();

            var $form = $(this),
                    $button = $form.find('#saveMessagesSubmit'),
                    data = {};

            if ($form.valid() && !$button.attr('disabled')) {
                $status.hide().removeClass('alert-success alert-danger');
                $button.attr('disabled', true);
                $button.val('Saving...');

                data.countryISO = "${country.countryISO}"

                $form.find('#countryName').each(function () {
                    data.countryName = this.value;
                });

                $form.find('#countryISO').each(function () {
                    data.countryISO = this.value;
                });

                $form.find('#allergenFoodGroups').each(function () {
                    data.listedHighRiskFoods = this.value.trim().split('\n');
                });

                $form.find('#specialInfo').each(function () {
                    data.specialInfo = this.value.trim().split('\n');
                });

                $form.find('#nationalWebsite').each(function () {
                    data.linkToNationalAllergenHealthWebsite = this.value.trim();
                });

                $.ajax({
                    url: resourceUrl,
                    type: 'PUT',
                    contentType: 'application/json; charset=UTF-8',
                    data: JSON.stringify(data)
                }).always(function (response) {
                    if (response === "OK") {
                        $status.addClass('alert-success').html('Messages saved').show().delay(5000).fadeOut();
                    } else {
                        $status.addClass('alert-danger').html('A error ocurred').show();
                    }
                    $button.attr('disabled', false);
                    $button.val('Save');
                });
            }
        });

        $('#deleteMessagesSubmit').click(function (ev) {
            ev.preventDefault();

            var $button = $(ev.currentTarget),
                    confirmation = confirm("You are going to delete all current allergy advice information, are you sure?");

            if (confirmation && !$button.attr('disabled')) {
                $status.hide().removeClass('alert-success alert-danger');
                $button.attr('disabled', true);
                $button.val('Deleting...');

                $.ajax({
                    url: resourceUrl + "?id=${country.countryISO}",
                    type: 'DELETE'
                }).always(function (response) {
                    if (response === "OK") {
                        $status.addClass('alert-success').html('Messages deleted').show().delay(5000).fadeOut();
                        $('textarea').val('');
                    } else {
                        $status.addClass('alert-danger').html('A error ocurred').show();
                    }
                    $button.attr('disabled', false);
                    $button.val('Delete');
                });
            }
        });
    });

</script>
</body>
</html>