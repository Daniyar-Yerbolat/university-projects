<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
<xsl:key name = "program_id" match = "program" use = "@programID"/>
<xsl:param name="line_number" select="0"></xsl:param>
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
	<h1>MEMBERS THAT TAKE MORE THAN 1 COURSE</h1>
		<body>
		<table align='center'>
			<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Course ID</th>
			<th>Course Name</th>
			</tr>
			<xsl:for-each select="member[count(prog[completed = 'No']) > 1]">
				<xsl:for-each select="prog[completed = 'No']">
				<tr>
					<td><xsl:value-of select="../firstname"/></td>
					<td><xsl:value-of select="../lastname"/></td>
					<td><xsl:value-of select="progID"/></td>
					<td><xsl:value-of select="key('program_id', progID)/programName"/></td>
				</tr>
				</xsl:for-each>
			</xsl:for-each>
			</table>
		</body>
	</html>
	</xsl:template>
</xsl:stylesheet>