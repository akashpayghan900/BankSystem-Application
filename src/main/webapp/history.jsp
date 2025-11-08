<%-- PDF Download Page (Styled to match Bank Dashboard & Deposit Page) --%>
<!DOCTYPE html>
<html>
<head>
    <title>Download PDF</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', Arial, sans-serif;
            background: url('https://www.policycircle.org/wp-content/uploads/2023/03/BankingSector.jpg') no-repeat center center/cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .pdf-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.2);
            text-align: center;
            width: 400px;
            backdrop-filter: blur(5px);
        }

        h2 {
            color: skyblue;
            margin-bottom: 25px;
            font-size: 24px;
            font-weight: bold;
        }

        .btn {
            background: orange;
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 10px;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s ease;
            margin-top: 15px;
        }

        .btn:hover {
            background: blue;
            transform: scale(1.05);
        }

        a {
            text-decoration: none;
            color: #1565c0;
            font-weight: bold;
            display: inline-block;
            margin-top: 20px;
        }

        a:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>
    <div class="pdf-container">
        <h2>Download Your Transaction Report</h2>
        <form action="PDFDownloadServlet" method="get" style="display:inline;">
            <button class="btn">Download PDF</button>
        </form>
        <br>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
