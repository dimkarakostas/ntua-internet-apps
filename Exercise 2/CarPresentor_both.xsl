<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
<head>
<title>Chop Shop</title>
</head>
<body>
<div class="results" style="text-align: center; float: center;">
<h1>Used Vehicles</h1>
<table align="center" border="2">
<tr>
<th>Year</th>
<th>Make</th>
<th>Model</th>
<th>Mileage</th>
<th>Color</th>
<th>Price</th>
</tr>
<xsl:for-each select="vehicles/vehicle">
<tr>
<td><xsl:value-of select="@year"/></td>
<td><xsl:value-of select="@make"/></td>
<td><xsl:value-of select="@model"/></td>
<td><xsl:value-of select="mileage"/></td>
<td><xsl:value-of select="color"/></td>
<td><xsl:value-of select="price"/></td>
</tr>
</xsl:for-each>
</table>
</div>
</body>
</html>
</xsl:template>
</xsl:stylesheet>