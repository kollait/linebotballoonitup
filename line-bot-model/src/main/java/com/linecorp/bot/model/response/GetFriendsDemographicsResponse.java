/*
 * Copyright 2019 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.bot.model.response;

import static java.util.Collections.emptyList;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = GetFriendsDemographicsResponse.GetFriendsDemographicsResponseBuilder.class)
public class GetFriendsDemographicsResponse {

    @Value
    @Builder
    @JsonDeserialize(builder = GenderTile.GenderTileBuilder.class)
    static class GenderTile {
        String gender;
        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class GenderTileBuilder {
            // Filled by lombok
        }
    }

    @Value
    @Builder
    @JsonDeserialize(builder = AgeTile.AgeTileBuilder.class)
    static class AgeTile {
        String age;
        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class AgeTileBuilder {
            // Filled by lombok
        }
    }

    @Value
    @Builder
    @JsonDeserialize(builder = AreaTile.AreaTileBuilder.class)
    static class AreaTile {
        String area;
        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class AreaTileBuilder {
            // Filled by lombok
        }
    }

    @Value
    @Builder
    @JsonDeserialize(builder = AppTypeTile.AppTypeTileBuilder.class)
    static class AppTypeTile {
        String appType;
        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class AppTypeTileBuilder {
            // Filled by lombok
        }
    }

    @Value
    @Builder
    @JsonDeserialize(builder = SubscriptionPeriodTile.SubscriptionPeriodTileBuilder.class)
    static class SubscriptionPeriodTile {
        String subscriptionPeriod;
        Double percentage;

        @JsonPOJOBuilder(withPrefix = "")
        public static class SubscriptionPeriodTileBuilder {
            // Filled by lombok
        }
    }

    /**
     * true if friend demographic information is available.
     */
    boolean available;

    /**
     * Percentage per gender.
     */
    @Default
    List<GenderTile> genders = emptyList();

    /**
     * Percentage per age group.
     */
    @Default
    List<AgeTile> ages = emptyList();

    /**
     * Percentage per area.
     */
    @Default
    List<AreaTile> areas = emptyList();

    /**
     * Percentage by OS.
     */
    @Default
    List<AppTypeTile> appTypes = emptyList();

    /**
     * Percentage per friendship duration.
     */
    @Default
    List<SubscriptionPeriodTile> subscriptionPeriods = emptyList();

    @JsonPOJOBuilder(withPrefix = "")
    public static class GetFriendsDemographicsResponseBuilder {
        // Filled by lombok
    }
}
