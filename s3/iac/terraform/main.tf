#got to terraform registry and search for provides to get aws setup code
#click aws and user provider at right top corner
#click on latest version and copy the code

terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "6.28.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}

#How to run terraform
# terraform init
# terraform plan
# terraform apply
# terraform destroy