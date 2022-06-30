import boto3
import os
from datetime import datetime, timezone, timedelta
from decimal import Decimal
import config


def lambda_handler(event, context):
    # Boto3 clients
    ec2_client = boto3.client('ec2')
    get_instances = ec2_client.describe_instances()
    create_capacity_reservation(get_instances, ec2_client)


# Get All the running instances and call the function to reserve capacity
def create_capacity_reservation(get_instances, client):
    running_instances = False
    cancel_reservations = []
    #Total Count of instances running
    t2_micro = t3_2xlarge = r6i_2xlarge = r6i_4xlarge = r6i_8xlarge = 0
    
    
    for reservations in get_instances['Reservations']:
        #Create Capacity Reservation for running instance types
        for instances in reservations['Instances']:
            if instances['State']['Name'] == 'running':
                running_instances = True
                type = instances['InstanceType']
                if type == "t2.micro":
                    t2_micro += 1
                elif type == "t3.2xlarge":
                    t3_2xlarge += 1
                elif type == "r6i.2xlarge":
                    r6i_2xlarge += 1
                elif type == "r6i.4xlarge":
                    r6i_4xlarge += 1
                elif type == "r6i_8xlarge":
                    r6i_8xlarge += 1
                    
                
                get_reservation = get_reservations(client, instances['InstanceType'])
                print(get_reservation)
                if not get_reservation['CapacityReservations']:
                    #print("No Capacity")
                    #Call reserver capacity function
                    
                    
                    
                    reserve_capacity(client, instances['InstanceType'])
                    
                    
                    
                elif get_reservation:
                    for reservation in get_reservation['CapacityReservations']:
                        cancel_reservations.remove(reservation['CapacityReservationId'])
                        print("It is here")
                       # modify_capacity_reservation(client, reservation['TotalInstanceCount'], 20, reservation['CapacityReservationId'])
                        modify_capacity_reservation(client, reservation['TotalInstanceCount'], reservation['AvailableInstanceCount'], reservation['CapacityReservationId'])
                         
            else:
                get_reservation = get_reservations(client, instances['InstanceType'])
                if get_reservation:
                    for reservation in get_reservation['CapacityReservations']:
                        if(reservation['CapacityReservationId'] not in cancel_reservations):
                            cancel_reservations.append(reservation['CapacityReservationId'])
    
    
    for reservations in get_instances['Reservations']:
        #Cancel Cpacity Reservation for stopped instance types
        for instances in reservations['Instances']: 
            get_reservation = get_reservations(client, instances['InstanceType'])
            if get_reservation:
                for reservation in cancel_reservations:
                    cancel_reservation(client, reservation)
                    
    if running_instances == False:
        print('No Running Instances found!')



def cancel_reservation(client, reservation_id):
    response = client.cancel_capacity_reservation(
    CapacityReservationId=reservation_id
    )
    return response
    


def modify_capacity_reservation(ec2_client, total_count, available_count, reservation_id):
    print ("Invoked!")
    default_count = config.default_count
    capacity_chg_percentage = config.capacity_chg_percentage
    
    #Calculate 20% capacity of total capacity
    capacity_percentage = int((config.capacity_chg_percentage / 100 * total_count))
    #Calculate available capacity percentage
    available_percentage = int((available_count / total_count * 100))
    
    print (capacity_percentage)
    print (available_percentage)

    #If capacity is less than 0 for example total count is low like 2 then round
    #the value to 1
    if (capacity_percentage == 0):
        capacity_percentage = 1
    elif (available_percentage == 0):
        available_percentage = 1
    
    #Increment 20% Capacity
    if (available_percentage <= capacity_chg_percentage):
        new_count = total_count + capacity_percentage
    
    #Decrement 20% Capacity
    elif (available_percentage > capacity_chg_percentage and total_count > default_count):
        new_count = total_count -capacity_percentage
    
    else:
        #Else don't change capacity
        new_count = total_count
    
    
    print (new_count)
    modify = ec2_client.modify_capacity_reservation(
        CapacityReservationId=reservation_id,
        InstanceCount=new_count
    )
  



def get_reservations(client, instance_type):
    
    response = client.describe_capacity_reservations(Filters=[
        {
            'Name': 'state',
            'Values': [
                'active',
            ]
        },
        {
            'Name': 'instance-type',
            'Values': [
                instance_type,
            ]
        },
    ]
    
    )
    
    return response


# Create Capacity Reservation
def reserve_capacity(client, instance_type):
    
    default_count = config.default_count
    
    response = client.create_capacity_reservation(
        InstanceType=instance_type,
        InstancePlatform=config.InstancePlatform,
        AvailabilityZone=config.AvailabilityZone,
        Tenancy=config.Tenancy,
        InstanceCount=default_count,
        EbsOptimized=config.EbsOptimized,
        EphemeralStorage=config.EphemeralStorage,
        EndDateType=config.EndDateType,
        InstanceMatchCriteria=config.InstanceMatchCriteria
    )









