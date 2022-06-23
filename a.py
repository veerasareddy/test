import boto3
import os
from datetime import datetime, timezone, timedelta


def lambda_handler(event, context):
    # Boto3 clients

    ec2_client = boto3.client('ec2')
    get_instances = ec2_client.describe_instances()
    # create_capacity_reservation(get_instances, ec2_client)
    get_reservervations_Id(ec2_client)


# Get All the running instances and call the function to reserve capacity
def create_capacity_reservation(get_instances, client):
    for reservations in get_instances['Reservations']:
        for instances in reservations['Instances']:
            if instances['State']['Name'] == 'running':
                print(get_instances['Reservations'])
                # reserve_capacity(client, instances['InstanceType'])


def get_reservervations_Id(client):
    
    response = client.describe_reserved_instances(
    DryRun=False,
    Filters=[{'Name': 'availability-zone', 'Values': ['us-east-1a']}]
    )

    print(response)


# Create Capacity Reservation
def reserve_capacity(client, instance_type):
    response = client.create_capacity_reservation(
        # ClientToken='string',
        InstanceType=instance_type,
        InstancePlatform='Linux/UNIX',
        AvailabilityZone='us-east-1a',
        # AvailabilityZoneId='string',
        Tenancy='default',
        InstanceCount=10,
        EbsOptimized=False,
        EphemeralStorage=False,
        # EndDate=datetime(2015, 1, 1),
        EndDateType='unlimited',
        InstanceMatchCriteria='open',
        # DryRun=True|False,
        # OutpostArn='string',
        # PlacementGroupArn='string'
    )









