<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Contacts List for ${userEmail}</title>
</head>
<body>
<p>Dear Admin,</p>
<p>List of Contacts for User with ID#: ${userId}</p>
<table style="table-layout: fixed; width: 400px">
    <#list userContacts as contact>
    <tr>
        <td>ContactID#:</td>
        <td>${contact.contactId}</td>
    </tr>
    <tr>
        <td>Name:</td>
        <td>${contact.firstName} ${contact.lastName}</td>
    </tr>
    <tr>
        <td>Phone Number:</td>
        <td>${contact.phoneNumber}</td>
    </tr>
    <tr>
        <td>Email:</td>
        <td>${contact.email}</td>
    </tr>
    <tr>
        <td>Address:</td>
        <td>${contact.address}</td>
    </tr>
    <tr style="height: 10px">
        <td colspan="2"></td>
    </tr>
    </#list>
</table>
<br>
<p>
    <small>
        <em>This message is sent by our automatic system.
            If it was sent to you in error, delete it immediately and report to
            raafatwahb@gmail.com
        </em>
    </small>
</p>
</body>
</html>
