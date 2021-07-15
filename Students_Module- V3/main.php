<!DOCTYPE html>
<?php include "./config.php" ?>
<html lang="en">
<head>
    
    <link rel = "stylesheet" href = "style.css" />
    <title>student login</title>
</head>
<body>

    <h1 style="text-align: center">RESULTS</h1>
    
    <div class="container">
    <div class="row">
    <div class="col-lg-8">
    <!-- show data here -->
    <div class="col-lg-8">
    
    
<!-- Employee detail form -->
    <form  class="empdetail"  name ="empdetail" id="emp" action="<?php $_SERVER['PHP_SELF']?>" method="POST" >
        
        <div class ="form-group">
            <label for="id">Enter the RollNo:</label>
            <input name="id" id="id" type="text" class="form-control" placeholder="RollNo" >
        </div>
    <br>

       
        <div class ="form-group">
        <button name="submit" id="submit" class="btn btn-primary btn-primary">SUBMIT</button>
        </div>
    </form>
    </div>
    </div>
    </div>
    </div>


     <table class="table-style" >
    <thead>
      <tr>
        <th>Student ID</th>
        <th>Student Name</th>
        <th>Exam Tpe</th>
        <th>Subject 1</th>
        <th>Subject 2</th>
        <th>Subject 3</th>
        <th>Subject 4</th>
        <th>Total</th>
        <th>Percentage</th> 
        <th>Remark</th>       
        
      </tr>
    </thead>
    <tbody>
    <?php 
        $query = "SELECT * FROM students";
        $fire = mysqli_query($con,$query) or die("can not fetch data from database. ".mysqli_error($con));

        //if ($fire) echo "We got the data from database.";
        if (mysqli_num_rows($fire)>0)
        {

                while($employee1 = mysqli_fetch_assoc($fire)){ ?>
                
                    <tr>

                        <td><?php echo $employee1['student_id'] ?> </td>
                        <td><?php echo $employee1['student_name'] ?> </td>
                        <td><?php echo $employee1['exam_type'] ?> </td>
                        <td><?php echo $employee1['sub1'] ?> </td>
                        <td><?php echo $employee1['sub2'] ?> </td>
                        <td><?php echo $employee1['sub3'] ?> </td>
                        <td><?php echo $employee1['sub4'] ?> </td>
                        <td><?php echo $employee1['total'] ?> </td>
                        <td><?php echo $employee1['percentage'] ?> </td>
                        <td><?php echo $employee1['remarks'] ?> </td>
                        <td>
                           
                    <tr>
                    <?php
                }
            }
        ?>
    </tbody>
    </table>
    
</body>
</html>