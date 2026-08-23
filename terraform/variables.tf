variable "aws_region" { type = string, default = "ap-south-1" }
variable "ubuntu_ami" { type = string, description = "Ubuntu AMI for the selected AWS region" }
variable "instance_type" { type = string, default = "t2.micro" }
variable "key_name" { type = string }
variable "ssh_cidr" { type = string, description = "Trusted CIDR for SSH and lab HTTP access" }
