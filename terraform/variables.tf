variable "aws_region" {
  type    = string
  default = "ap-south-1"
}

variable "ubuntu_ami" {
  type        = string
  default     = null
  description = "Ubuntu AMI for the selected AWS region. Leave null to auto-resolve the latest Ubuntu 22.04 LTS AMI."
}

variable "instance_type" {
  type    = string
  default = "t2.micro"
}

variable "key_name" {
  type        = string
  description = "Name of an existing EC2 key pair in this AWS account/region, used for SSH access"
}

variable "ssh_cidr" {
  type        = string
  description = "Trusted CIDR for SSH and lab HTTP access, e.g. YOUR_PUBLIC_IP/32"
}
