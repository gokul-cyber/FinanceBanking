terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

resource "aws_instance" "test_server" {
  ami                    = var.ubuntu_ami
  instance_type          = var.instance_type
  key_name               = var.key_name
  vpc_security_group_ids = [aws_security_group.financeme.id]
  tags = { Name = "FinanceMe-test" }
}

resource "aws_instance" "prod_server" {
  ami                    = var.ubuntu_ami
  instance_type          = var.instance_type
  key_name               = var.key_name
  vpc_security_group_ids = [aws_security_group.financeme.id]
  tags = { Name = "FinanceMe-prod" }
}

resource "aws_security_group" "financeme" {
  name = "financeme-web"
  ingress { from_port = 22 to_port = 22 protocol = "tcp" cidr_blocks = [var.ssh_cidr] }
  ingress { from_port = 8080 to_port = 8080 protocol = "tcp" cidr_blocks = [var.ssh_cidr] }
  egress { from_port = 0 to_port = 0 protocol = "-1" cidr_blocks = ["0.0.0.0/0"] }
}

output "test_public_ip" { value = aws_instance.test_server.public_ip }
output "prod_public_ip" { value = aws_instance.prod_server.public_ip }
