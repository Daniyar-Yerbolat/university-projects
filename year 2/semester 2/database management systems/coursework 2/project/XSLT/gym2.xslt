<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
<xsl:key name = "dept_id" match = "department" use = "@deptID"/>
<!-- CHANGE THIS VALUE FOR DIFFERENT RESULTS -->
<xsl:param name="dept" select="2"></xsl:param>
	<xsl:template match="/gym">
        <html>
        	<head>
        		<title>GYM</title>
        		<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet"> </link>
        		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> </link>
        		<style>
        			body {
        			background-color: #F2F1EF;
        			text-align: center;
        			}
        			
        			h1 {
        			color: #D91E18;
        			font-family: 'Oswald', sans-serif;
        			}
        			
        			table {
        			border-collapse: collapse;
        			}
        			
        			th, td {
        			padding: 8px;
        			text-align: center;
        			border-bottom: 1px solid #abb7b7;
        			font-family: 'Roboto', sans-serif;
        			}
        			
        			p{
        			font-family: 'Roboto', sans-serif;
        			}
        		</style>
        	</head>
        <body>
		<h1>Staff That Are In Department Of <xsl:value-of select="key('dept_id', $dept)/departmentName"/></h1>
		<table align='center'>
			<tr>
			<th></th>
			<th>First Name</th>
			<th>Last Name</th>
			</tr>
			
		<xsl:for-each select="staff[deptID=$dept]">
			<tr>
				<td><xsl:number count="staff[deptID=$dept]"/></td>
				<td><xsl:value-of select="firstname"/></td>
				<td><xsl:value-of select="lastname"/></td>
			</tr>
		</xsl:for-each>
			
		</table>
        </body>
		</html>
	</xsl:template>
</xsl:stylesheet>