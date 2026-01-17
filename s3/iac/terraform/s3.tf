#got to terraform registry and search for provides to get aws setup code and then got to documentation and. s3 bucket

resource "aws_s3_bucket" "my-s3-bucket" {
  tags = {
    Name        = "My bucket"
    Environment = "Dev"
  }
}
